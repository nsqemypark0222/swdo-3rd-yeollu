package com.yeollu.getrend.mango.service;

import java.util.ArrayList;

import com.yeollu.getrend.mango.vo.MangoStoreVO;

public interface MangoService {
	public ArrayList<MangoStoreVO> updateMangoStores();
	
	public void updateMangoDays(ArrayList<MangoStoreVO> mangoStoreList);
	
	public void updateMangoTimes(ArrayList<MangoStoreVO> mangoStoreList);
}
