package com.yeollu.getrend.store.dao;

import com.yeollu.getrend.store.vo.SearchedVO;

public interface SearchedMapper {
	public String isExistedSearchedTerm(String store_name);
	public int insertSearchedTerm(SearchedVO searchedTerm);
}
