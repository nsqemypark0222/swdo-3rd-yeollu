package com.yeollu.getrend.mango.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yeollu.getrend.mango.service.MangoServiceImpl;
import com.yeollu.getrend.mango.vo.MangoStoreVO;

/**
 * @Class 	: MangoController.java
 * @Package	: com.yeollu.getrend.mango.controller
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 4. 19.
 * @Version	: 1.0
 * @Desc	: 망고플레이트에서 수집한 정보를 저장하는 테이블들을 관리한다.
 */
@Controller
public class MangoController {

	/**
	 * Fields
	 */
	@Autowired
	private MangoServiceImpl mangoService;
	
	/**
	 * @Method	: update
	 * @Return	: void
	 * @Author	: 박민열
	 * @Since	: 2020. 4. 19.
	 * @Version	: 1.0
	 * @Desc	: mango_stores, mango_days, mango_times 테이블을 함께 갱신한다.
	 */
	public void update() {
		ArrayList<MangoStoreVO> mangoStoreList = mangoService.updateMangoStores();
		mangoService.updateMangoDays(mangoStoreList);
		mangoService.updateMangoTimes(mangoStoreList);
	}
}
