package com.yeollu.getrend.mango.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yeollu.getrend.mango.controller.MangoController;

@Component
public class MangoScheduler {
	
	@Autowired
	private MangoController mangoController;

//	@Scheduled(cron = "0/30 * * * * *")
	@Scheduled(cron = "0 15 10 15 * ?")
	public void executeJob() {
		mangoController.update();
	}
}
