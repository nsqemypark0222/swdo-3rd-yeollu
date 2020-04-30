package com.yeollu.getrend.user.dao;

import com.yeollu.getrend.user.vo.UserVO;

/**
 * @Class 	: UserMapper.java
 * @Package	: com.yeollu.getrend.user.dao
 * @Project : GeTrend
 * @Author	: 오선미
 * @Since	: 2020. 3. 12.
 * @Version	: 1.0
 * @Desc	: users 테이블의 Mapper interface 역할을 수행한다.
 */
public interface UserMapper {
	public int join(UserVO user);
	public int updateUser(UserVO user);
	public int updateSocialUser(UserVO user);
	public int deleteUser(String user_email);
	public UserVO selectEmail(String user_email) ;
	public UserVO selectName(String user_name) ;
	public String isExistedUserName(String user_name);
}
