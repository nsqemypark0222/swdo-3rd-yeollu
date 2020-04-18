package com.yeollu.getrend.store.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeollu.getrend.crawler.CrawlerExecutor;
import com.yeollu.getrend.store.dao.InstaLocationDAO;
import com.yeollu.getrend.store.dao.MangoStoreDAO;
import com.yeollu.getrend.store.dao.ScoreDAO;
import com.yeollu.getrend.store.dao.StoreDAO;
import com.yeollu.getrend.store.service.StoreService;
import com.yeollu.getrend.store.util.map.core.Polygon;
import com.yeollu.getrend.store.util.map.model.Point;
import com.yeollu.getrend.store.util.preprocess.core.QueryStringSender;
import com.yeollu.getrend.store.vo.InstaImageVO;
import com.yeollu.getrend.store.vo.InstaLocationVO;
import com.yeollu.getrend.store.vo.InstaStoreInfoVO;
import com.yeollu.getrend.store.vo.InstaStoreVO;
import com.yeollu.getrend.store.vo.MangoStoreInfoVO;
import com.yeollu.getrend.store.vo.PostImageVO;
import com.yeollu.getrend.store.vo.ReqParmVO;
import com.yeollu.getrend.store.vo.ScoreVO;
import com.yeollu.getrend.store.vo.StoreVO;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private StoreDAO storeDAO;
	
	@Autowired
	private StoreService storeService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpSession session, Model model) {
		
		
		return "home";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<InstaStoreInfoVO> search(@RequestBody ReqParmVO reqParm, HttpSession session) {
		logger.info("검색 시작");
		long startTime = System.currentTimeMillis();
		
		if(session.getAttribute("istores") != null) {
			logger.info("current istores : {}", session.getAttribute("istores"));
			session.removeAttribute("istores");
		}
		
		ArrayList<Point> points = reqParm.getPoints();
		ArrayList<String> categoryValues = reqParm.getCategoryValues();
		ArrayList<String> opentimeValues = reqParm.getOpentimeValues();
		logger.info("points : {}", points);
		logger.info("categoryValues : {}", categoryValues);
		logger.info("opentimeValues : {}", opentimeValues);
		
		// DB에 저장된 모든 상가 리스트 중에서 카테고리에 해당되는 상가들만 조회
		ArrayList<StoreVO> storeList = storeDAO.selectStoresByStoreCate1(categoryValues);
		logger.info("storeList size : {}", storeList.size());
		
		// View로부터 넘겨받은 다각형의 꼭지점을 이용해 좌표상의 다각형 생성하여 판별
		Polygon polygon = new Polygon();
		for(Point point : points) {
			polygon.addPoint(point);
		}
		
		// 상가 리스트(storeList) 중에서 다각형 내부에 존재하는 상가들만 추출 => selectedStoreList
		ArrayList<StoreVO> selectedStoreList = new ArrayList<StoreVO>();
		for(StoreVO store : storeList) {
			if (polygon.isContains(store.getStore_x(), store.getStore_y())) {
				selectedStoreList.add(store);
			}
		}
		logger.info("selectedStoreList size : {}", selectedStoreList.size());
		
		// 인스타그램에 쿼리스트링을 보내 상가의 위치 정보 수집하여 location_id를 하나 리턴받아
		// InstaStore 객체 생성하여 instaStoreList에 수집
		ArrayList<InstaStoreVO> instaStoreList = storeService.generateInstaStoreList(selectedStoreList);
		
		// 망고플레이트 정보 추가
		ArrayList<MangoStoreInfoVO> mangoStoreInfoList = storeService.generateMangoStoreInfoList(instaStoreList, opentimeValues);
		
		// 좋아요와 별점을 기반으로 스코어가 높은 순으로 정렬
		ArrayList<ScoreVO> scoreList = storeService.generateScoreList(instaStoreList);
		instaStoreList = storeService.sortInstaStoreList(instaStoreList, scoreList);
		
		// 인스타그램 크롤링 요청
		ArrayList<InstaImageVO> instaImageList = storeService.requestCrawling(instaStoreList);

		// View로 보낼 최종 객체 리스트
		ArrayList<InstaStoreInfoVO> instaStoreInfoList = storeService.generateInstaStoreInfoList(instaStoreList, mangoStoreInfoList, scoreList, instaImageList);
		
		// 인스타그램 좋아요가 높은 순으로 재정렬
		instaStoreInfoList = storeService.sortInstaStoreInfoList(instaStoreInfoList);
		
		// 세션에 저장
		session.setAttribute("istores", instaStoreInfoList);

		long endTime = System.currentTimeMillis();
		long diff = (endTime - startTime) / 1000;
		logger.info("걸린 시간 : {}", diff);
		logger.info("검색 종료");

		return instaStoreInfoList;
	}
	
	
	
}
