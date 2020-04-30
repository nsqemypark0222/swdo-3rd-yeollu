package com.yeollu.getrend.mango.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yeollu.getrend.mango.controller.MangoController;

/**
 * @Class 	: MangoScheduler.java
 * @Package	: com.yeollu.getrend.mango.scheduler
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 4. 19.
 * @Version	: 1.0
 * @Desc	: 정해진 날짜에 예약된 망고플레이트 웹 크롤링 작업을 수행하여 관련 테이블들을 갱신한다.
 */
@Component
public class MangoScheduler {
	
	/**
	 * Fields
	 */
	@Autowired
	private MangoController mangoController;

	/**
	 * @Method	: executeJob
	 * @Return	: void
	 * @Author	: 박민열
	 * @Since	: 2020. 4. 19.
	 * @Version	: 1.0
	 * @Desc	: 매달 15일 오전 10시 15분에 예약된 작업을 수행한다.
	 */
	@Scheduled(cron = "0 15 10 15 * ?")
	public void executeJob() {
		mangoController.update();
	}
}
