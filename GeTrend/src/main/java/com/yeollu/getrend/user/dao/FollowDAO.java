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
<<<<<<< HEAD

	
	public int insertFollow(FollowVO follow){
=======
	
	public int insertFollow(FollowVO follow) {
>>>>>>> 877bf820be8f9722b06f516873ca0fc2d76fb8f3
		int cnt = 0;
		try {
			FollowMapper mapper = session.getMapper(FollowMapper.class);
			cnt = mapper.insertFollow(follow);
		} catch (Exception e) {
			e.printStackTrace();
<<<<<<< HEAD
		}return cnt;
	}
	
	public int deleteFollow(FollowVO follow){
=======
		}
		return cnt;
	}
	public int deleteFollow(FollowVO follow) {
>>>>>>> 877bf820be8f9722b06f516873ca0fc2d76fb8f3
		int cnt = 0;
		try {
			FollowMapper mapper = session.getMapper(FollowMapper.class);
			cnt = mapper.deleteFollow(follow);
		} catch (Exception e) {
			e.printStackTrace();
<<<<<<< HEAD
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
=======
		}
		return cnt;
>>>>>>> 877bf820be8f9722b06f516873ca0fc2d76fb8f3
	}
}
