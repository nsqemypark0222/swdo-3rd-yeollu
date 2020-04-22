package com.yeollu.getrend.store.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.yeollu.getrend.store.vo.RecommendVO;

public interface RecommendMapper {
	public int insertRecommend(RecommendVO recommend);
	
	public ArrayList<RecommendVO> selectAllRecommend();
	
	public ArrayList<RecommendVO> selectRecommendByStoreAdr(String store_adr);
	
	public ArrayList<RecommendVO> selectRecommendByStoreCate1(String store_cate1);
	
	public ArrayList<HashMap<String, Object>> selectRecommendStore(String store_adr);
}
