package com.yeollu.getrend.store.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.store.vo.MangoTimeVO;

@Repository
public class MangoTimeDAO {
	@Autowired
	private SqlSession session;
	
	public int insertMangoTime(MangoTimeVO mangoTime) {
		int cnt = 0;
		try {
			MangoTimeMapper mapper = session.getMapper(MangoTimeMapper.class);
			cnt = mapper.insertMangoTime(mangoTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
}
