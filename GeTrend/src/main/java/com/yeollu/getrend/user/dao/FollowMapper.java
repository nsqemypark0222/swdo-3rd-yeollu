package com.yeollu.getrend.user.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.yeollu.getrend.user.vo.FollowVO;

public interface FollowMapper {

	public int insertFollow(FollowVO follow);
	public int deleteFollow(FollowVO follow);
	public int checkFollow(FollowVO follow);
	public int countFollower(String user_email);
	public int countFollow(String user_email);
	public ArrayList<HashMap<String, Object>> selectFollowing(String user_email); 		//user_email가 팔로잉한 사람
	public ArrayList<HashMap<String, Object>> selectFollower(String follows_following);  //follows_following를 팔로우한 사람
}
