package com.yeollu.getrend.user.dao;

import com.yeollu.getrend.user.vo.FollowVO;

public interface FollowMapper {

	public int insertFollow(FollowVO follow);
	
	public int deleteFollow(FollowVO follow);
	
}
