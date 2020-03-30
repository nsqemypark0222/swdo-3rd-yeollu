package com.yeollu.getrend.store.dao;

import java.util.ArrayList;

import com.yeollu.getrend.store.vo.InstaStoreVO;
import com.yeollu.getrend.store.vo.StoreVO;

public interface StoreMapper {
	public ArrayList<StoreVO> selectAllStores();
	public InstaStoreVO selectInstaStore(String store_no);
	public ArrayList<String> autoStoreName();
	public ArrayList<String> autoStoreAdr();
	public ArrayList<String> autoStoreCate2_01();
	public ArrayList<String> autoStoreCate2_02();
	public ArrayList<String> autoStoreCate2_03();
}
