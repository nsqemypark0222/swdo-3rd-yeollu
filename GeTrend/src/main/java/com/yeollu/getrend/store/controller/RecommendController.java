package com.yeollu.getrend.store.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeollu.getrend.store.dao.StoreDAO;
import com.yeollu.getrend.store.service.RecommendServiceImpl;
import com.yeollu.getrend.store.service.StoreService;
import com.yeollu.getrend.store.vo.InstaStoreInfoVO;
import com.yeollu.getrend.store.vo.RecommendVO;

@Controller
public class RecommendController {
	
	private static final Logger logger = LoggerFactory.getLogger(RecommendController.class);
	
	@Autowired
	private StoreDAO storeDAO;
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private RecommendServiceImpl recommendService;
	
	public void update() {
		ArrayList<RecommendVO> recommendList = recommendService.selectAllRecommendList();
		
		for(RecommendVO recommend : recommendList) {
			recommendService.updateRecommendStores(recommend);
		}
	}
	
	@RequestMapping(value = "/recommendByAccessLocation", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<InstaStoreInfoVO> recommendByAccessLocation(RecommendVO recommend, HttpSession session) {
		logger.info("위치 기반 추천 시작 : {}", recommend);
		long startTime = System.currentTimeMillis();
		
		ArrayList<InstaStoreInfoVO> instaStoreInfoList = recommendService.generateInstaStoreInfo(recommend);
		session.setAttribute("istores", instaStoreInfoList);
		session.setAttribute("accessLocationFlag", "false");
		
		long endTime = System.currentTimeMillis();
		long diff = (endTime - startTime) / 1000;
		logger.info("걸린 시간 : {}", diff);
		logger.info("위치 기반 추천 종료");

		return instaStoreInfoList;
	}
	
	@RequestMapping(value = "recommend", method = RequestMethod.GET)
	public String recommend(RecommendVO recommend, Model model) {
		logger.info("추천 시작 : {}, {}", recommend.getStore_adr(), recommend.getStore_cate1());
		long startTime = System.currentTimeMillis();
		
		ArrayList<InstaStoreInfoVO> instaStoreInfoList = recommendService.generateInstaStoreInfo(recommend);
		
		model.addAttribute("istores", instaStoreInfoList);
		
		long endTime = System.currentTimeMillis();
		long diff = (endTime - startTime) / 1000;
		logger.info("걸린 시간 : {}", diff);
		
		logger.info("추천 종료");
		
		return "/autosearchForm";
	}
}
