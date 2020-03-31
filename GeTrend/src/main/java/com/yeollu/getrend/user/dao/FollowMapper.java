package com.yeollu.getrend.user.dao;

import com.yeollu.getrend.user.vo.FollowVO;

public interface FollowMapper {

	public int insertFollow02(FollowVO follow);
	public int deleteFollow02(FollowVO follow);
	public int countFollower(String user_email);
	public int countFollow(String user_email);
}
