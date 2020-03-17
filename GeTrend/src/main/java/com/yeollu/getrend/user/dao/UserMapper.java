package com.yeollu.getrend.user.dao;

import com.yeollu.getrend.user.vo.UserVO;

public interface UserMapper {
	public int join(UserVO user);
	public UserVO selectOne(String user_id) ;
	public int updateUser(UserVO user);
	public int deleteUser(String user_id);
}
