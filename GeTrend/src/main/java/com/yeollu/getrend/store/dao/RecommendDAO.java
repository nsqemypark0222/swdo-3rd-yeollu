package com.yeollu.getrend.store.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.store.vo.RecommendVO;

@Repository
public class RecommendDAO {

	@Autowired
	private SqlSession session;
	
	public int insertRecommend(RecommendVO recommend) {
		int cnt = 0;
		
		try {
			RecommendMapper mapper = session.getMapper(RecommendMapper.class);
			cnt = mapper.insertRecommend(recommend);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public ArrayList<RecommendVO> selectAllRecommend() {
		ArrayList<RecommendVO> list = null;
		
		try {
			RecommendMapper mapper = session.getMapper(RecommendMapper.class);
			list = mapper.selectAllRecommend();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList<RecommendVO> selectRecommendByStoreAdr(String store_adr) {
		ArrayList<RecommendVO> list = null;
		
		try {
			RecommendMapper mapper = session.getMapper(RecommendMapper.class);
			list = mapper.selectRecommendByStoreAdr(store_adr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList<RecommendVO> selectRecommendByStoreCate1(String store_cate1) {
		ArrayList<RecommendVO> list = null;
		
		try {
			RecommendMapper mapper = session.getMapper(RecommendMapper.class);
			list = mapper.selectRecommendByStoreCate1(store_cate1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList<HashMap<String, Object>> selectRecommendStore(String store_adr) {
		ArrayList<HashMap<String, Object>> resultMapList = null;
		
		try {
			RecommendMapper mapper = session.getMapper(RecommendMapper.class);
			resultMapList = mapper.selectRecommendStore(store_adr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMapList;
	}
}
