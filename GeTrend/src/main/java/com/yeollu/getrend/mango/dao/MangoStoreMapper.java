package com.yeollu.getrend.mango.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.yeollu.getrend.mango.vo.MangoDayVO;
import com.yeollu.getrend.mango.vo.MangoStoreVO;
import com.yeollu.getrend.store.vo.MangoStoreInfoVO;

public interface MangoStoreMapper {
	public int insertMangoStore(MangoStoreVO mangoStore);
	public ArrayList<MangoStoreVO> selectAllMangoStores();
	public MangoStoreVO selectMangoStoreByStoreNo(String store_no);
	public ArrayList<MangoStoreVO> selectMangoStoreByMangoDay(MangoDayVO mangoDay);
	public int updateMangoStore(MangoStoreVO mangoStore);
}
