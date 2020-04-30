package com.yeollu.getrend.store.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.yeollu.getrend.store.vo.RecommendVO;

/**
 * @Class 	: RecommendMapper.java
 * @Package	: com.yeollu.getrend.store.dao
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 4. 19.
 * @Version	: 1.0
 * @Desc	: recommend 테이블의 Mapper interface 역할을 수행한다
 */
public interface RecommendMapper {
	public int insertRecommend(RecommendVO recommend);
	public ArrayList<RecommendVO> selectAllRecommend();
	public ArrayList<RecommendVO> selectRecommendByStoreAdr(String store_adr);
	public ArrayList<RecommendVO> selectRecommendByStoreCate1(String store_cate1);
	public ArrayList<HashMap<String, Object>> selectRecommendStore(String store_adr);
}
