package com.yeollu.getrend.store.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.store.vo.MangoStoreVO;

@Repository
public class MangoStoreDAO {
	
	@Autowired
	private SqlSession session;
	
	public int insertMangoStore(MangoStoreVO mangoStore) {
		int cnt = 0;
		
		try {
			MangoStoreMapper mapper = session.getMapper(MangoStoreMapper.class);
			cnt = mapper.insertMangoStore(mangoStore);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public MangoStoreVO selectMangoStoreByStoreNo(String store_no) {
		MangoStoreVO mangoStore = new MangoStoreVO();
		
		try {
			MangoStoreMapper mapper = session.getMapper(MangoStoreMapper.class);
			mangoStore = mapper.selectMangoStoreByStoreNo(store_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mangoStore;
	}

}
