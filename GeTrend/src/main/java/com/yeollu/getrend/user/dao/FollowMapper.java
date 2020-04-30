package com.yeollu.getrend.user.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.yeollu.getrend.user.vo.FollowVO;

/**
 * @Class 	: FollowMapper.java
 * @Package	: com.yeollu.getrend.user.dao
 * @Project : GeTrend
 * @Author	: 오선미, 조은채
 * @Since	: 2020. 3. 31.
 * @Version	: 1.0
 * @Desc	: follows 테이블의 Mapper interface 역할을 수행한다.
 */
public interface FollowMapper {
	public int insertFollow(FollowVO follow);
	public int deleteFollow(FollowVO follow);
	public int checkFollow(FollowVO follow);
	public int countFollower(String user_email);
	public int countFollow(String user_email);
	public ArrayList<HashMap<String, Object>> selectFollowing(String user_email); 		//user_email가 팔로잉한 사람
	public ArrayList<HashMap<String, Object>> selectFollower(String follows_following);  //follows_following를 팔로우한 사람
}
