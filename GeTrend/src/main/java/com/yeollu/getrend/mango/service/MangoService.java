package com.yeollu.getrend.mango.service;

import java.util.ArrayList;

import com.yeollu.getrend.mango.vo.MangoStoreVO;

/**
 * @Class 	: MangoService.java
 * @Package	: com.yeollu.getrend.mango.service
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 4. 19.
 * @Version	: 1.0
 * @Desc	: 망고플레이트 관련 작업을 수행하기 위한 Service interface 역할을 수행한다.
 */
public interface MangoService {
	public ArrayList<MangoStoreVO> updateMangoStores();
	public void updateMangoDays(ArrayList<MangoStoreVO> mangoStoreList);
	public void updateMangoTimes(ArrayList<MangoStoreVO> mangoStoreList);
}
