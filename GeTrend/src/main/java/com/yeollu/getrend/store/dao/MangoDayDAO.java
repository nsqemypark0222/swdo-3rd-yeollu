package com.yeollu.getrend.store.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.store.vo.MangoDayVO;

@Repository
public class MangoDayDAO {

	@Autowired
	private SqlSession session;
	
	public int insertMangoDay(MangoDayVO mangoDay) {
		int cnt = 0;
		try {
			MangoDayMapper mapper = session.getMapper(MangoDayMapper.class);
			cnt = mapper.insertMangoDay(mangoDay);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
}
