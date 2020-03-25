package com.yeollu.getrend.store.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.store.vo.SearchedStoreVO;

@Repository
public class SearchedStoreDAO {
	@Autowired
	private SqlSession session;
	
	public boolean isExistedSearchedStore(String store_name) {
		boolean result = false;
		
		try {
			SearchedStoreMapper mapper = session.getMapper(SearchedStoreMapper.class);
			if(mapper.isExistedSearchedStore(store_name).equals("true")) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int insertSearchedStore(SearchedStoreVO searchedStore) {
		int cnt = 0;
		
		try {
			SearchedStoreMapper mapper = session.getMapper(SearchedStoreMapper.class);
			cnt = mapper.insertSearchedStore(searchedStore);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
}
