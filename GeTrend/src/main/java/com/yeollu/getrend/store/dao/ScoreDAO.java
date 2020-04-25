package com.yeollu.getrend.store.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ScoreDAO {
	@Autowired
	private SqlSession session;
	
	public int selectCountLikeByStoreNo(String store_no) {
		int cnt = 0;
		try {
			ScoreMapper mapper = session.getMapper(ScoreMapper.class);
			cnt = mapper.selectCountLikeByStoreNo(store_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	public double scoreAvgByStoreno(String store_no)  {
		double avg = 0;
		try {
			ScoreMapper mapper = session.getMapper(ScoreMapper.class);
			avg = mapper.scoreAvgByStoreno(store_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return avg;
	}
}
