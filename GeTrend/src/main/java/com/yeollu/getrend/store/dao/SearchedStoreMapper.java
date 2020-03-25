package com.yeollu.getrend.store.dao;

import com.yeollu.getrend.store.vo.SearchedStoreVO;

public interface SearchedStoreMapper {
	public String isExistedSearchedStore(String store_name);
	public int insertSearchedStore(SearchedStoreVO searchedStore);
}
