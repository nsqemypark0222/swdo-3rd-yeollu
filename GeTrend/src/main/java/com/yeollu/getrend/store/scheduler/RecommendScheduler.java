package com.yeollu.getrend.store.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yeollu.getrend.store.controller.RecommendController;

/**
 * @Class 	: RecommendScheduler.java
 * @Package	: com.yeollu.getrend.store.scheduler
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 4. 19.
 * @Version	: 1.0
 * @Desc	: 가게 추천 관련 작업을 제어한다.
 */
@Component
public class RecommendScheduler {
	
	/**
	 * Fields
	 */
	private static final Logger logger = LoggerFactory.getLogger(RecommendController.class);
	@Autowired
	private RecommendController recommendController;
	
	/**
	 * @Method	: executeJob
	 * @Return	: void
	 * @Author	: 박민열
	 * @Since	: 2020. 4. 19.
	 * @Version	: 1.0
	 * @Desc	: 6시간마다 예약된 크롤링 작업을 수행해 데이터베이스를 갱신한다.
	 */
//	@Scheduled(fixedDelay = 86400000)
	@Scheduled(cron = "0 0 0/6 * * *")
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
