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

	
	
=======
>>>>>>> 18151576be3b0e59d8f2036632a733429ed68226
	public int insertFollow(FollowVO follow) {
		int cnt = 0;
		try {
			FollowMapper mapper = session.getMapper(FollowMapper.class);
			cnt = mapper.insertFollow(follow);
		} catch (Exception e) {
			e.printStackTrace();
		}return cnt;
	}
<<<<<<< HEAD

=======
<<<<<<< HEAD
	

=======
>>>>>>> 18151576be3b0e59d8f2036632a733429ed68226
>>>>>>> a03a108b5ef1276210cd8d2bd4f992199ba5a72f
	public int deleteFollow(FollowVO follow) {
		int cnt = 0;
		try {
			FollowMapper mapper = session.getMapper(FollowMapper.class);
			cnt = mapper.deleteFollow(follow);
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
<<<<<<< HEAD
		}

		return cnt;
=======
		}return cnt;
>>>>>>> 18151576be3b0e59d8f2036632a733429ed68226
	}
}