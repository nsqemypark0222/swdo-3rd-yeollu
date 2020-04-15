package com.yeollu.getrend.store.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yeollu.getrend.crawler.CrawlerExecutor;
import com.yeollu.getrend.store.dao.InstaLocationDAO;
import com.yeollu.getrend.store.dao.MangoStoreDAO;
import com.yeollu.getrend.store.dao.StoreDAO;
import com.yeollu.getrend.store.util.preprocess.core.QueryStringSender;
import com.yeollu.getrend.store.vo.InstaImageVO;
import com.yeollu.getrend.store.vo.InstaLocationVO;
import com.yeollu.getrend.store.vo.InstaStoreInfoVO;
import com.yeollu.getrend.store.vo.InstaStoreVO;
import com.yeollu.getrend.store.vo.MangoStoreInfoVO;
import com.yeollu.getrend.store.vo.StoreVO;

@Controller
public class RecommendController {
	
	private static final Logger logger = LoggerFactory.getLogger(RecommendController.class);
	
	@Autowired
	private StoreDAO storeDAO;
	
	@Autowired
	private InstaLocationDAO instaLocationDAO;

	@Autowired
	private MangoStoreDAO mangoStoreDAO;
	
	@RequestMapping(value = "recommendByCategory", method = RequestMethod.GET)
	public String recommendByCategory(@RequestParam(value = "recommendByCategoryKeyword") String keyword, HttpSession session) {
		logger.info("카테고리 추천 시작 : {}", keyword);
		long startTime = System.currentTimeMillis();
		
		ArrayList<String> categoryValues = new ArrayList<String>();
		categoryValues.add(keyword);
		// DB에 저장된 모든 상가 리스트 중에서 카테고리에 해당되는 상가들만 조회
		ArrayList<StoreVO> storeList = storeDAO.selectStoresByStoreCate1(categoryValues);
		logger.info("storeList size : {}", storeList.size());
		
		
		
		
		
		if(storeList.size() > 3) {
			storeList = new ArrayList<StoreVO> (storeList.subList(0, 3));
		}
		
		
		
		
		
		// 인스타그램에 쿼리스트링을 보내 상가의 위치 정보 수집하여 location_id를 하나 리턴받아
		// InstaStore 객체 생성하여 instaStoreList에 수집
		ArrayList<InstaStoreVO> instaStoreList = new ArrayList<InstaStoreVO>();
		for (StoreVO store : storeList) {
			String location_id = QueryStringSender.send(store);
			if (location_id == null || location_id.equals("")) {
			} else {
				if (!instaLocationDAO.isExistedInstaLocation(location_id)) {
					InstaLocationVO instaLocation = new InstaLocationVO();
					instaLocation.setLocation_id(location_id);
					instaLocation.setStore_no(store.getStore_no());
					instaLocationDAO.insertInstaLocation(instaLocation);
				}
				InstaStoreVO instaStore = storeDAO.selectInstaStore(store.getStore_no());
				if (instaStore != null) {
					instaStore.setLocation_id(location_id);
					instaStoreList.add(instaStore);
				}
			}
		}
		logger.info("instaStoreList size : {}", instaStoreList.size());

		// 망고플레이트 정보 추가 + 크롤링 요청할 로케이션 아이디 리스트 생성
		ArrayList<MangoStoreInfoVO> mangoStoreInfoList = new ArrayList<MangoStoreInfoVO>();
		ArrayList<String> locationList = new ArrayList<String>();
		for(InstaStoreVO instaStore : instaStoreList) {
			MangoStoreInfoVO mangoStoreInfo = new MangoStoreInfoVO();
			mangoStoreInfo = mangoStoreDAO.selectMangoStoreInfoByStoreNo(instaStore.getStore_no());
			mangoStoreInfoList.add(mangoStoreInfo);
			locationList.add(instaStore.getLocation_id());
		}

		// 인스타그램 크롤링 요청
		ArrayList<InstaImageVO> instaImageList = new ArrayList<InstaImageVO>();
		ArrayList<CrawlerExecutor> crawlerExecutorList = new ArrayList<CrawlerExecutor>();
		for (String location : locationList) {
			CrawlerExecutor crawlerExecutor = new CrawlerExecutor();
			crawlerExecutor.setLocation(location);
			new Thread(crawlerExecutor, "crawling :  " + location).start();
			crawlerExecutorList.add(crawlerExecutor);
		}
		for (CrawlerExecutor crawlerExecutor : crawlerExecutorList) {
			instaImageList.add(crawlerExecutor.getInstaImage());
		}
		CrawlerExecutor.killChromeDriver();

		// View로 보낼 최종 객체 리스트
		ArrayList<InstaStoreInfoVO> instaStoreInfoList = new ArrayList<InstaStoreInfoVO>();
		try {
			for (int i = 0; i < instaStoreList.size(); i++) {
				InstaStoreInfoVO instaStoreInfo = new InstaStoreInfoVO();
				instaStoreInfo.setInstaStore(instaStoreList.get(i));
				instaStoreInfo.setMangoStoreInfo(mangoStoreInfoList.get(i));

				if (instaImageList.size() > i) {
					instaStoreInfo.setInstaImage(instaImageList.get(i));
				} else {
					instaStoreInfo.setInstaImage(null);
				}
				logger.info("instaStoreInfo name : {}", instaStoreInfo.getInstaStore().getStore_name());
				instaStoreInfoList.add(instaStoreInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("instaStoreInfoList size : {}", instaStoreInfoList.size());
		session.setAttribute("istores", instaStoreInfoList);

		long endTime = System.currentTimeMillis();
		long diff = (endTime - startTime) / 1000;
		logger.info("걸린 시간 : {}", diff);
		
		logger.info("카테고리 추천 종료");
		
		return "/autosearchForm";
	}
	
	@RequestMapping(value = "recommendByAdr", method = RequestMethod.GET)
	public String recommendByAdr(@RequestParam(value = "recommendByAdrKeyword") String keyword) {
		logger.info("법정동 추천 시작 : {}", keyword);
		
		
		
		logger.info("법정동 추천 종료");
		
		return "/autosearchForm";
	}

}
