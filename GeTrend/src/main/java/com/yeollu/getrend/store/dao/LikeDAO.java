package com.yeollu.getrend.store.dao;


import java.util.ArrayList;

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
	
	public ArrayList<LikeVO> likeSelectByEmail(String user_email){
		ArrayList<LikeVO> list = new ArrayList<LikeVO>();
		
		try {
			LikeMapper mapper = session.getMapper(LikeMapper.class);
			list = mapper.likeSelectByEmail(user_email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList<LikeVO> likeSelectByStoreno(String store_no){
		ArrayList<LikeVO> list = new ArrayList<LikeVO>();
		
		try {
			LikeMapper mapper = session.getMapper(LikeMapper.class);
			list = mapper.likeSelectByStoreno(store_no);
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
	
}
