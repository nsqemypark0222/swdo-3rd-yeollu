package com.yeollu.getrend.store.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.store.vo.SearchedVO;

@Repository
public class SearchedDAO {
	@Autowired
	private SqlSession session;
	
	public boolean isExistedSearchedTerm(String store_name) {
		boolean result = false;
		
		try {
			SearchedMapper mapper = session.getMapper(SearchedMapper.class);
			if(mapper.isExistedSearchedTerm(store_name).equals("true")) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int insertSearchedTerm(SearchedVO searchedTerm) {
		int cnt = 0;
		
		try {
			SearchedMapper mapper = session.getMapper(SearchedMapper.class);
			cnt = mapper.insertSearchedTerm(searchedTerm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
}
