package com.yeollu.getrend.store.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.store.vo.InstaUserVO;

@Repository
public class InstaUserDAO {
	@Autowired
	private SqlSession session;
	
	public int insertInstaUser(InstaUserVO instaUser) {
		int cnt = 0;
		
		try {
			InstaUserMapper mapper = session.getMapper(InstaUserMapper.class);
			cnt = mapper.insertInstaUser(instaUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public boolean isExistedInstaUserId(String store_no) {
		boolean result = false;
		
		try {
			InstaUserMapper mapper = session.getMapper(InstaUserMapper.class);
			if(mapper.isExistedInstaUserId(store_no).equals("true")) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
