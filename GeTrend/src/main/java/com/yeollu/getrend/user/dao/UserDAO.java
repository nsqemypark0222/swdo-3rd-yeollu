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
		
		if(user.getUser_profile() == null) {
			user.setUser_profile("https://res.cloudinary.com/dw5oh4ebf/image/upload/v1586757006/user_alnoxs.png");
			user.setUser_profileId("1586757006");
		}
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			cnt = mapper.join(user);
		} catch (Exception e) {
			e.printStackTrace();
		}return cnt;
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
	
	public int updateSocialUser(UserVO user) {
		int cnt = 0;
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			cnt = mapper.updateSocialUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}return cnt;
	}
	
	public int deleteUser(String user_email) {
		int cnt = 0;
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			cnt = mapper.deleteUser(user_email);
		} catch (Exception e) {
			e.printStackTrace();
		}return cnt;
	}
	public UserVO selectEmail(String user_email) {
		UserVO user = null;
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			user = mapper.selectEmail(user_email);
		} catch (Exception e) {
			e.printStackTrace();
		}return user;
	}
	
	public UserVO selectName(String user_name){
		UserVO user = null;
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			user = mapper.selectName(user_name);
		} catch (Exception e) {
			e.printStackTrace();
		}return user;
	}
	
	public boolean isExistedUserName(String user_name) {
		boolean result = false;
		
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			if(mapper.isExistedUserName(user_name).equals("true")) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
