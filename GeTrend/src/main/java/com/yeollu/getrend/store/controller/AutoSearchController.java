package com.yeollu.getrend.store.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yeollu.getrend.crawler.CrawlerExecutor;
import com.yeollu.getrend.store.dao.InstaLocationDAO;
import com.yeollu.getrend.store.dao.MangoStoreDAO;
import com.yeollu.getrend.store.dao.ScoreDAO;
import com.yeollu.getrend.store.dao.StoreDAO;
import com.yeollu.getrend.store.service.StoreService;
import com.yeollu.getrend.store.util.preprocess.core.QueryStringSender;
import com.yeollu.getrend.store.vo.InstaImageVO;
import com.yeollu.getrend.store.vo.InstaLocationVO;
import com.yeollu.getrend.store.vo.InstaStoreInfoVO;
import com.yeollu.getrend.store.vo.InstaStoreVO;
import com.yeollu.getrend.store.vo.MangoStoreInfoVO;
import com.yeollu.getrend.store.vo.PostImageVO;
import com.yeollu.getrend.store.vo.ScoreVO;
import com.yeollu.getrend.store.vo.StoreVO;

@Controller
public class AutoSearchController {
	private static final Logger logger = LoggerFactory.getLogger(AutoSearchController.class);
	
	@Autowired
	private StoreDAO storeDAO;
	
	@Autowired
	private StoreService storeService;
	
	@RequestMapping(value = "autoSearch", method = RequestMethod.GET)
	public String autoSearch(String searchInput,HttpSession session) {
		logger.info(searchInput);
		long startTime = System.currentTimeMillis();
		
		if(session.getAttribute("istores") != null) {
			logger.info("current istores : {}", session.getAttribute("istores"));
			session.removeAttribute("istores");
		}
		
		ArrayList<StoreVO> storeList = storeDAO.searchStoresByTerm(searchInput);
		logger.info("{}",storeList);
		
		// 인스타그램에 쿼리스트링을 보내 상가의 위치 정보 수집하여 location_id를 하나 리턴받아
		// InstaStore 객체 생성하여 instaStoreList에 수집
		ArrayList<InstaStoreVO> instaStoreList = storeService.generateInstaStoreList(storeList);
		
		// 망고플레이트 정보 추가
		ArrayList<MangoStoreInfoVO> mangoStoreInfoList = storeService.generateMangoStoreInfoList(instaStoreList, null);
		
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
				
		return "/autosearchForm";
	}
}
