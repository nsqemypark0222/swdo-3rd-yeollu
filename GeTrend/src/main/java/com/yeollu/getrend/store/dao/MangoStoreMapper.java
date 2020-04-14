package com.yeollu.getrend.store.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.yeollu.getrend.store.vo.MangoDayVO;
import com.yeollu.getrend.store.vo.MangoStoreInfoVO;
import com.yeollu.getrend.store.vo.MangoStoreVO;

public interface MangoStoreMapper {
	public int insertMangoStore(MangoStoreVO mangoStore);
	public ArrayList<MangoStoreVO> selectAllMangoStores();
	public MangoStoreInfoVO selectMangoStoreByStoreNo(String store_no);
	public ArrayList<MangoStoreVO> selectMangoStoreByMangoDay(MangoDayVO mangoDay);
	public MangoStoreInfoVO selectMangoStoreInfoByStoreNo(String store_no);
	public MangoStoreInfoVO selectMangoStoreInfoByStoreNoAndDays(HashMap<String, String> map);
}
