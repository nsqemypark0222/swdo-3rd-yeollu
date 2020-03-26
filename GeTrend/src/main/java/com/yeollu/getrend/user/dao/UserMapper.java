package com.yeollu.getrend.user.dao;

import com.yeollu.getrend.user.vo.UserVO;

public interface UserMapper {
	public int join(UserVO user);
	public int updateUser(UserVO user);
	public int deleteUser(String user_email);
	public UserVO selectEmail(String user_email) ;
}
