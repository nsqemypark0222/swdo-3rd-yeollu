package com.yeollu.getrend.store.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yeollu.getrend.store.service.RecommendServiceImpl;
import com.yeollu.getrend.store.vo.InstaStoreInfoVO;
import com.yeollu.getrend.store.vo.RecommendVO;

@Controller
public class RecommendController {
	
	private static final Logger logger = LoggerFactory.getLogger(RecommendController.class);
	
	@Autowired
	private RecommendServiceImpl recommendService;
	
	public void update() {
		ArrayList<RecommendVO> recommendList = recommendService.selectAllRecommendList();
		
		for(RecommendVO recommend : recommendList) {
			recommendService.updateRecommendStores(recommend);
		}
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
