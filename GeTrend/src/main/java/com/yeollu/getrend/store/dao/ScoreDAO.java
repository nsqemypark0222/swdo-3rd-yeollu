package com.yeollu.getrend.store.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.store.vo.ScoreVO;

@Repository
public class ScoreDAO {
	@Autowired
	private SqlSession session;
	
	public ScoreVO selectScoreByStoreNo(String store_no) {
		ScoreVO score = null;
		try {
			ScoreMapper mapper = session.getMapper(ScoreMapper.class);
			score = mapper.selectScoreByStoreNo(store_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return score;
	}
	
	public int scoreAvgByStoreno(String store_no)  {
		int cnt = 0;
		try {
			ScoreMapper mapper = session.getMapper(ScoreMapper.class);
			cnt = mapper.scoreAvgByStoreno(store_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
}
