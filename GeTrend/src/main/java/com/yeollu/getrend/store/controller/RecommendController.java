package com.yeollu.getrend.store.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RecommendController {
	
	private static final Logger logger = LoggerFactory.getLogger(RecommendController.class);
	
	@RequestMapping(value = "recommendByCategory", method = RequestMethod.GET)
	public String recommendByCategory(@RequestParam(value = "recommendByCategoryKeyword") String keyword) {
		logger.info("카테고리 추천 시작 : {}", keyword);
		
		
		
		
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
