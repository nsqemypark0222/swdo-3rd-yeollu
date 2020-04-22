package com.yeollu.getrend.store.dao;

import com.yeollu.getrend.store.vo.ScoreVO;

public interface ScoreMapper {
	public ScoreVO selectScoreByStoreNo(String store_no);
	public int scoreAvgByStoreno(String store_no);
}
