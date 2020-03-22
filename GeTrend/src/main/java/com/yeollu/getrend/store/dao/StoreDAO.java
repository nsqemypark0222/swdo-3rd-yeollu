package com.yeollu.getrend.store.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.store.vo.StoreVO;

@Repository
public class StoreDAO {
	@Autowired
	private SqlSession session;
	
	public ArrayList<StoreVO> selectAllStores() {
		ArrayList<StoreVO> list = new ArrayList<StoreVO>();
		
		try {
			StoreMapper mapper = session.getMapper(StoreMapper.class);
			list = mapper.selectAllStores();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}