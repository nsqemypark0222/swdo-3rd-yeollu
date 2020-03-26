package com.yeollu.getrend.store.dao;

import java.util.ArrayList;

import com.yeollu.getrend.store.vo.InstaStoreVO;
import com.yeollu.getrend.store.vo.StoreVO;

public interface StoreMapper {
	public ArrayList<StoreVO> selectAllStores();
	public InstaStoreVO selectInstaStore(String store_no);
}
