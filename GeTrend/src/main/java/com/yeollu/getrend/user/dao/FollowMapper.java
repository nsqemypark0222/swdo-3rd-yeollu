package com.yeollu.getrend.user.dao;

import java.util.ArrayList;

import com.yeollu.getrend.user.vo.FollowVO;

public interface FollowMapper {

	public int insertFollow(FollowVO follow);
	public int deleteFollow(FollowVO follow);
	public int checkFollow(FollowVO follow);
	public int countFollower(String user_email);
	public int countFollow(String user_email);
	public ArrayList<FollowVO> selectFollowing(String user_email); 		//내가 팔로잉한 사람
	public ArrayList<FollowVO> selectFollow(String follows_following);  //나를 팔로우한 사람
}
