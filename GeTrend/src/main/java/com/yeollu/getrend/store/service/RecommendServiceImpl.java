package com.yeollu.getrend.store.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeollu.getrend.store.dao.RecommendDAO;
import com.yeollu.getrend.store.dao.StoreDAO;
import com.yeollu.getrend.store.vo.InstaImageVO;
import com.yeollu.getrend.store.vo.InstaStoreInfoVO;
import com.yeollu.getrend.store.vo.InstaStoreVO;
import com.yeollu.getrend.store.vo.MangoStoreInfoVO;
import com.yeollu.getrend.store.vo.RecommendVO;
import com.yeollu.getrend.store.vo.ScoreVO;
import com.yeollu.getrend.store.vo.StoreVO;

@Service
public class RecommendServiceImpl implements RecommendService {
	private static final Logger logger = LoggerFactory.getLogger(RecommendServiceImpl.class);
	
	@Autowired
	private StoreDAO storeDAO;
	
	@Autowired
	private StoreServiceImpl storeService;
	
	@Autowired
	private RecommendDAO recommendDAO;
	
	@Override
	public ArrayList<RecommendVO> selectAllRecommendList() {
		return recommendDAO.selectAllRecommend();
	}
	
	@Override
	public void updateRecommendStores(RecommendVO recommend) {
		logger.info("추천 업데이트 시작 : {}, {}", recommend.getStore_adr(), recommend.getStore_cate1());
		long startTime = System.currentTimeMillis();
		ArrayList<StoreVO> storeList = storeDAO.selectStoresByStoreAdrAndStoreCate1(recommend.getStore_adr(), recommend.getStore_cate1());
		
		ArrayList<InstaStoreVO> instaStoreList = storeService.generateInstaStoreList(storeList);
		
		storeService.generateScoreList(instaStoreList);
		
		storeService.requestCrawling(instaStoreList);
		
		long endTime = System.currentTimeMillis();
		long diff = (endTime - startTime) / 1000;
		logger.info("걸린 시간 : {}", diff);
		logger.info("추천 업데이트 종료");
	}
	
	@Override
	public ArrayList<InstaStoreInfoVO> generateInstaStoreInfo(RecommendVO recommend) {
		logger.info("추천 조회 시작");
		long startTime = System.currentTimeMillis();
		
		ArrayList<StoreVO> storeList = null;
		if(recommend.getStore_cate1() == null || recommend.getStore_cate1().equals("")) {
			storeList = storeDAO.selectStoresByStoreAdr(recommend.getStore_adr());
		} else {
			storeList = storeDAO.selectStoresByStoreAdrAndStoreCate1(recommend.getStore_adr(), recommend.getStore_cate1());
		}
		
		ArrayList<InstaStoreVO> instaStoreList = storeService.generateInstaStoreList(storeList);
		
		ArrayList<MangoStoreInfoVO> mangoStoreInfoList = storeService.generateMangoStoreInfoList(instaStoreList, null);
		
		ArrayList<ScoreVO> scoreList = storeService.generateScoreList(instaStoreList);
		
		instaStoreList = storeService.sortInstaStoreList(instaStoreList, scoreList);
		
		ArrayList<InstaImageVO> instaImageList = storeService.requestCrawling(instaStoreList);
		
		ArrayList<InstaStoreInfoVO> instaStoreInfoList = storeService.generateInstaStoreInfoList(instaStoreList, mangoStoreInfoList, scoreList, instaImageList);
		
		instaStoreInfoList = storeService.sortInstaStoreInfoList(instaStoreInfoList);
		
		long endTime = System.currentTimeMillis();
		long diff = (endTime - startTime) / 1000;
		logger.info("걸린 시간 : {}", diff);
		logger.info("추천 조회 종료");
		
		return instaStoreInfoList;
	}
	
}
