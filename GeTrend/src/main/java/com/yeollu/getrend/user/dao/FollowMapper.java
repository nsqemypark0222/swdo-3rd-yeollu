package com.yeollu.getrend.user.dao;

import com.yeollu.getrend.user.vo.FollowVO;

public interface FollowMapper {

	public int insertFollow(FollowVO follow);
<<<<<<< HEAD
	public int deleteFollow(FollowVO follow);
	public int countFollower(String user_email);
	public int countFollow(String user_email);
=======
	
	public int deleteFollow(FollowVO follow);
	
>>>>>>> 877bf820be8f9722b06f516873ca0fc2d76fb8f3
}
