package com.yeollu.getrend.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.user.vo.FollowVO;
import com.yeollu.getrend.user.vo.UserVO;

@Repository
public class FollowDAO {
	@Autowired
	private SqlSession session;

	public int insertFollow02(FollowVO follow) {
		int cnt = 0;
		try {
			FollowMapper mapper = session.getMapper(FollowMapper.class);
			cnt = mapper.insertFollow02(follow);
		} catch (Exception e) {
			e.printStackTrace();
		}return cnt;
	}
	
	
	public int deleteFollow02(FollowVO follow) {
		int cnt = 0;
		try {
			FollowMapper mapper = session.getMapper(FollowMapper.class);
			cnt = mapper.deleteFollow02(follow);
		} catch (Exception e) {
			e.printStackTrace();
		}return cnt;
	}
	
	public int countFollow(String user_email){
		int cnt = 0;
		try {
			FollowMapper mapper = session.getMapper(FollowMapper.class);
			cnt = mapper.countFollow(user_email);
		} catch (Exception e) {
			e.printStackTrace();
		}return cnt;
	}
	public int countFollower(String user_email){
		int cnt = 0;
		try {
			FollowMapper mapper = session.getMapper(FollowMapper.class);
			cnt = mapper.countFollower(user_email);
		} catch (Exception e) {
			e.printStackTrace();
		}return cnt;
	}
}
