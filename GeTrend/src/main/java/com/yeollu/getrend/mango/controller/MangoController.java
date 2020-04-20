package com.yeollu.getrend.mango.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.yeollu.getrend.mango.service.MangoServiceImpl;
import com.yeollu.getrend.mango.vo.MangoStoreVO;

@Controller
public class MangoController {

	@Autowired
	private MangoServiceImpl mangoService;
	
	public void update() {
		ArrayList<MangoStoreVO> mangoStoreList = mangoService.updateMangoStores();
		mangoService.updateMangoDays(mangoStoreList);
		mangoService.updateMangoTimes(mangoStoreList);
	}
}
