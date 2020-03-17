package com.yeollu.getrend.store.dao;

import java.util.ArrayList;

import com.yeollu.getrend.store.vo.StoreVO;

public interface StoreMapper {
	public ArrayList<StoreVO> selectAllStores();
}
