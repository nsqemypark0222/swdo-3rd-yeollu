package com.yeollu.getrend.store.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.store.vo.InstaUserInfoVO;

@Repository
public class InstaUserInfoDAO {
	
	@Autowired
	private SqlSession session;
	
	public int insertInstaUserInfo(InstaUserInfoVO instaUserInfo) {
		int cnt = 0;
		
		try {
			InstaUserInfoMapper mapper = session.getMapper(InstaUserInfoMapper.class);
			cnt = mapper.insertInstaUserInfo(instaUserInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
}
