package com.yeollu.getrend.store.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.store.vo.InstaLocationInfoVO;

@Repository
public class InstaLocationInfoDAO {

	@Autowired
	private SqlSession session;
	
	public int insertInstaLocationInfo(InstaLocationInfoVO instaLocationInfo) {
		int cnt = 0;
		
		try {
			InstaLocationInfoMapper mapper = session.getMapper(InstaLocationInfoMapper.class);
			cnt = mapper.insertInstaLocationInfo(instaLocationInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
}
