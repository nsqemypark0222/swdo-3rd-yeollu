package com.yeollu.getrend.store.dao;

/**
 * @Class 	: ScoreMapper.java
 * @Package	: com.yeollu.getrend.store.dao
 * @Project : GeTrend
 * @Author	: 조은채, 박민열
 * @Since	: 2020. 4. 11.
 * @Version	: 1.0
 * @Desc	: scores 테이블의 Mapper interface 역할을 수행한다
 */
public interface ScoreMapper {
	public int selectCountLikeByStoreNo(String store_no);
	public double scoreAvgByStoreNo(String store_no);
}
