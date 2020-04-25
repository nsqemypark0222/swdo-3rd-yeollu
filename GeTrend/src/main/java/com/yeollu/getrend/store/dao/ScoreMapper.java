package com.yeollu.getrend.store.dao;

public interface ScoreMapper {
	public int selectCountLikeByStoreNo(String store_no);
	public double scoreAvgByStoreno(String store_no);
}
