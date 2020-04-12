package com.yeollu.getrend.store.dao;


import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.store.vo.LikeVO;


@Repository
public class LikeDAO {

	@Autowired
	private SqlSession session;
	
	public int likeInsert(LikeVO like) {
		int cnt = 0;
		
		try {
			LikeMapper mapper = session.getMapper(LikeMapper.class);
			cnt = mapper.likeInsert(like);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public int likeDelete(LikeVO like) {
		int cnt = 0;
		
		try {
			LikeMapper mapper = session.getMapper(LikeMapper.class);
			cnt = mapper.likeDelete(like);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public ArrayList<HashMap<String, Object>> likeSelectByEmail(String user_email){
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		
		try {
			LikeMapper mapper = session.getMapper(LikeMapper.class);
			list = mapper.likeSelectByEmail(user_email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int likeSelectByEmailStoreno(LikeVO like){
		int cnt = 0;
	try {
		LikeMapper mapper = session.getMapper(LikeMapper.class);
		cnt = mapper.likeSelectByEmailStoreno(like);
	} catch (Exception e) {
		e.printStackTrace();
	}		
	return cnt;
}
	
	
	public int likeStoreCountByEmail(String user_email){
			int cnt = 0;
		try {
			LikeMapper mapper = session.getMapper(LikeMapper.class);
			cnt = mapper.likeStoreCountByEmail(user_email);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return cnt;
	}
	
	public boolean isExistedLike(LikeVO like) {
		boolean result = false;
		try {
			LikeMapper mapper = session.getMapper(LikeMapper.class);
			if(mapper.isExistedLike(like).equals("true")) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
