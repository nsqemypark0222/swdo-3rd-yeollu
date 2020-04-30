package com.yeollu.getrend.user.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.user.vo.FollowVO;

/**
 * @Class 	: FollowDAO.java
 * @Package	: com.yeollu.getrend.user.dao
 * @Project : GeTrend
 * @Author	: 오선미, 조은채
 * @Since	: 2020. 3. 31.
 * @Version	: 1.0
 * @Desc	: follows 테이블에 접근하여 작업을 수행한다.
 */
@Repository
public class FollowDAO {
	
	/**
	 * Fields
	 */
	@Autowired
	private SqlSession session;

	/**
	 * @Method	: insertFollow
	 * @Return	: int
	 * @Author	: 오선미, 조은채
	 * @Since	: 2020. 3. 31.
	 * @Version	: 1.0
	 * @Desc	: follows 테이블에 데이터를 추가한다.
	 * @param follow
	 */
	public int insertFollow(FollowVO follow) {
		int cnt = 0;
		try {
			FollowMapper mapper = session.getMapper(FollowMapper.class);
			cnt = mapper.insertFollow(follow);
		} catch (Exception e) {
			e.printStackTrace();
		}return cnt;
	}

	/**
	 * @Method	: deleteFollow
	 * @Return	: int
	 * @Author	: 오선미, 조은채
	 * @Since	: 2020. 3. 31.
	 * @Version	: 1.0
	 * @Desc	: follows 테이블의 데이터를 삭제한다.
	 * @param follow
	 */
	public int deleteFollow(FollowVO follow) {
		int cnt = 0;
		try {
			FollowMapper mapper = session.getMapper(FollowMapper.class);
			cnt = mapper.deleteFollow(follow);
		} catch (Exception e) {
			e.printStackTrace();
		}return cnt;
	}
	
	/**
	 * @Method	: checkFollow
	 * @Return	: int
	 * @Author	: 오선미, 조은채
	 * @Since	: 2020. 3. 31.
	 * @Version	: 1.0
	 * @Desc	: follow 테이블의 follow한 사람인지 확인한다.
	 * @param follow
	 */
	public int checkFollow(FollowVO follow) {
		int ck = 0;
		try {
			FollowMapper mapper = session.getMapper(FollowMapper.class);
			ck = mapper.checkFollow(follow);			
		} catch (Exception e) {
			e.printStackTrace();
		}return ck;
	}
	
	/**
	 * @Method	: countFollow
	 * @Return	: int
	 * @Author	: 오선미, 조은채
	 * @Since	: 2020. 3. 31.
	 * @Version	: 1.0
	 * @Desc	: follows 테이블의 user_email가 follow한 사람의 수를 조회한다.
	 * @param user_email
	 */
	public int countFollow(String user_email){
		int cnt = 0;
		try {
			FollowMapper mapper = session.getMapper(FollowMapper.class);
			cnt = mapper.countFollow(user_email);
		} catch (Exception e) {
			e.printStackTrace();
		}return cnt;
	}
	
	/**
	 * @Method	: countFollower
	 * @Return	: int
	 * @Author	: 오선미, 조은채
	 * @Since	: 2020. 3. 31.
	 * @Version	: 1.0
	 * @Desc	: follows 테이블의 user_email를 follow 한 사람의 수를 조회한다.
	 * @param user_email
	 */
	public int countFollower(String user_email){
		int cnt = 0;
		try {
			FollowMapper mapper = session.getMapper(FollowMapper.class);
			cnt = mapper.countFollower(user_email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;

	}
	
	/**
	 * @Method	: selectFollowing
	 * @Return	: ArrayList<HashMap<String,Object>>
	 * @Author	: 오선미, 조은채
	 * @Since	: 2020. 3. 31.
	 * @Version	: 1.0
	 * @Desc	: follows 테이블에서 user_email이 팔로우한 사람 리스트를 조회한다.
	 * @param user_email
	 */
	public ArrayList<HashMap<String, Object>> selectFollowing(String user_email){
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		try {
			FollowMapper mapper = session.getMapper(FollowMapper.class);
			list = mapper.selectFollowing(user_email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * @Method	: selectFollower
	 * @Return	: ArrayList<HashMap<String,Object>>
	 * @Author	: 오선미, 조은채
	 * @Since	: 2020. 3. 31.
	 * @Version	: 1.0
	 * @Desc	: follows 테이블에서 follows_following를 팔로우한 사람 리스트를 조회한다. 
	 * @param follows_following
	 */
	public ArrayList<HashMap<String, Object>> selectFollower(String follows_following){
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		try {
			FollowMapper mapper = session.getMapper(FollowMapper.class);
			list = mapper.selectFollower(follows_following);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
	



