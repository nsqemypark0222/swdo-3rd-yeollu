package com.yeollu.getrend.store.dao;

import java.util.ArrayList;

import com.yeollu.getrend.store.vo.InstaStoreInfoVO;
import com.yeollu.getrend.store.vo.InstaStoreVO;
import com.yeollu.getrend.store.vo.StoreVO;

public interface StoreMapper {
	public ArrayList<StoreVO> selectAllStores();
	public ArrayList<InstaStoreInfoVO> selectInstaStoreInfo(String store_no);
}
