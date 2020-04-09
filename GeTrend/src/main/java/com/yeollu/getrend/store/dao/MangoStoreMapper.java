package com.yeollu.getrend.store.dao;

import java.util.ArrayList;

import com.yeollu.getrend.store.vo.MangoDayVO;
import com.yeollu.getrend.store.vo.MangoStoreVO;

public interface MangoStoreMapper {
	public int insertMangoStore(MangoStoreVO mangoStore);
	public ArrayList<MangoStoreVO> selectAllMangoStores();
	public MangoStoreVO selectMangoStoreByStoreNo(String store_no);
	public ArrayList<MangoStoreVO> selectMangoStoreByMangoDay(MangoDayVO mangoDay);
}
