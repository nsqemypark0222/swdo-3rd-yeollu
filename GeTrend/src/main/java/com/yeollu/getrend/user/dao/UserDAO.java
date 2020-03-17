package com.yeollu.getrend.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.user.vo.UserVO;

@Repository
public class UserDAO {
	@Autowired
	private SqlSession session;
	
	public int join(UserVO user) {
		int cnt = 0;
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			cnt = mapper.join(user);
		} catch (Exception e) {
			e.printStackTrace();
		}return cnt;
	}
	public UserVO selectOne(String user_id) {
		UserVO user = null;
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			user = mapper.selectOne(user_id);
		} catch (Exception e) {
			e.printStackTrace();
		}return user;
	}
	public int updateUser(UserVO user) {
		int cnt = 0;
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			cnt = mapper.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}return cnt;
	}
	
	public int deleteUser(String user_id) {
		int cnt = 0;
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			cnt = mapper.deleteUser(user_id);
		} catch (Exception e) {
			e.printStackTrace();
		}return cnt;
	}
}
