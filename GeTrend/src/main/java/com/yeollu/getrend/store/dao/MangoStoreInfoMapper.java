package com.yeollu.getrend.store.dao;

import java.util.HashMap;

import com.yeollu.getrend.store.vo.MangoStoreInfoVO;

public interface MangoStoreInfoMapper {
	public MangoStoreInfoVO selectMangoStoreInfoByStoreNo(String store_no);
	public MangoStoreInfoVO selectMangoStoreInfoByStoreNoAndDays(HashMap<String, String> map);
}
