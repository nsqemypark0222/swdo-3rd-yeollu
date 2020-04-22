package com.yeollu.getrend.store.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yeollu.getrend.store.controller.RecommendController;

@Component
public class RecommendScheduler {
	
	private static final Logger logger = LoggerFactory.getLogger(RecommendController.class);

	@Autowired
	private RecommendController recommendController;
	
	@Scheduled(fixedDelay = 86400000)
	public void executeJob() {
		
		logger.info("--------------------------------------------------------------------------추천 스케줄러 시작");
		long startTime = System.currentTimeMillis();
		
		recommendController.update();
		
		long endTime = System.currentTimeMillis();
		long diff = (endTime - startTime) / 1000;
		logger.info("걸린 시간 : {}", diff);
		
		logger.info("--------------------------------------------------------------------------추천 스케줄러 종료");
	}
}
