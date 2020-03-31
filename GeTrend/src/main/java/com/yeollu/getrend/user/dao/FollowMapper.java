package com.yeollu.getrend.user.dao;

import com.yeollu.getrend.user.vo.FollowVO;

public interface FollowMapper {

	public int insertFollow(FollowVO follow);
	public int deleteFollow(FollowVO follow);
	public int countFollower(String user_email);
	public int countFollow(String user_email);
}
