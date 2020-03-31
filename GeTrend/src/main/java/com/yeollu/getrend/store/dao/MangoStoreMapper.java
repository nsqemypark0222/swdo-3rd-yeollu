package com.yeollu.getrend.store.dao;

import com.yeollu.getrend.store.vo.MangoStoreVO;

public interface MangoStoreMapper {
	public int insertMangoStore(MangoStoreVO mangoStore);
	public MangoStoreVO selectMangoStoreByStoreNo(String store_no);
}
