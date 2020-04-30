package com.yeollu.getrend.store.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.store.vo.InstaReplyVO;

/**
 * @Class 	: InstaReplyDAO.java
 * @Package	: com.yeollu.getrend.store.dao
 * @Project : GeTrend
 * @Author	: 오선미, 조은채
 * @Since	: 2020. 3. 26.
 * @Version	: 1.0
 * @Desc	: insta_replys 테이블에 접근하여 관련 작업을 수행한다.
 */
@Repository
public class InstaReplyDAO {
	
	/**
	 * Fields
	 */
	@Autowired
	private SqlSession session;
	
	/**
	 * @Method	: replyWrite
	 * @Return	: int
	 * @Author	: 오선미, 조은채
	 * @Since	: 2020. 3. 26.
	 * @Version	: 1.0
	 * @Desc	: insta_replys 테이블에 데이터를 추가한다.
	 * @param reply
	 */
	public int replyWrite(InstaReplyVO reply) {
		int cnt = 0;
		try {
			InstaReplyMapper mapper = session.getMapper(InstaReplyMapper.class);
			cnt = mapper.replyWrite(reply);
		} catch (Exception e) {
			e.printStackTrace();
		}return cnt;
	}

	/**
	 * @Method	: replyRemove
	 * @Return	: int
	 * @Author	: 오선미, 조은채
	 * @Since	: 2020. 3. 26.
	 * @Version	: 1.0
	 * @Desc	: insta_replys 테이블에서 데이터를 삭제한다.
	 * @param reply
	 */
	public int replyRemove(InstaReplyVO reply) {
		int cnt = 0;
		try {
			InstaReplyMapper mapper = session.getMapper(InstaReplyMapper.class);
			cnt = mapper.replyRemove(reply);
		} catch (Exception e) {
			e.printStackTrace();
		}return cnt;
	}
	
	/**
	 * @Method	: replyList
	 * @Return	: ArrayList<HashMap<String,Object>>
	 * @Author	: 오선미, 조은채
	 * @Since	: 2020. 3. 26.
	 * @Version	: 1.0
	 * @Desc	: insta_replys 테이블에서 데이터를 조회한다.
	 * @param reply
	 */
	public ArrayList<HashMap<String,Object>> replyList(InstaReplyVO reply) {
		ArrayList<HashMap<String,Object>> rlist = null;
		try {
			InstaReplyMapper mapper = session.getMapper(InstaReplyMapper.class);
			rlist = mapper.replyList(reply);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rlist;
	}
	
	/**
	 * @Method	: replyListByEmail
	 * @Return	: ArrayList<HashMap<String,Object>>
	 * @Author	: 오선미, 조은채
	 * @Since	: 2020. 3. 26.
	 * @Version	: 1.0
	 * @Desc	: insta_replys 테이블에서 데이터를 조회한다.
	 * @param user_email
	 */
	public ArrayList<HashMap<String, Object>> replyListByEmail(String user_email){
		ArrayList<HashMap<String,Object>> rlist = null;
		try {
			InstaReplyMapper mapper = session.getMapper(InstaReplyMapper.class);
			rlist = mapper.replyListByEmail(user_email);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rlist;
	}
	
	/**
	 * @Method	: replyListByFollow
	 * @Return	: ArrayList<HashMap<String,Object>>
	 * @Author	: 오선미, 조은채
	 * @Since	: 2020. 3. 26.
	 * @Version	: 1.0
	 * @Desc	: insta_replys 테이블에서 데이터를 조회한다.
	 * @param user_email
	 */
	public ArrayList<HashMap<String, Object>> replyListByFollow(String user_email){
		ArrayList<HashMap<String,Object>> rlist = null;
		try {
			InstaReplyMapper mapper = session.getMapper(InstaReplyMapper.class);
			rlist = mapper.replyListByFollow(user_email);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rlist;
	}
	
	/**
	 * @Method	: replyListByLikedStore
	 * @Return	: ArrayList<HashMap<String,Object>>
	 * @Author	: 오선미, 조은채
	 * @Since	: 2020. 3. 26.
	 * @Version	: 1.0
	 * @Desc	: insta_replys 테이블에서 데이터를 조회한다.
	 * @param user_email
	 */
	public ArrayList<HashMap<String, Object>> replyListByLikedStore(String user_email){
		ArrayList<HashMap<String,Object>> rlist = null;
		try {
			InstaReplyMapper mapper = session.getMapper(InstaReplyMapper.class);
			rlist = mapper.replyListByLikedStore(user_email);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rlist;
	}
	
	/**
	 * @Method	: replyCountByStoreno
	 * @Return	: int
	 * @Author	: 오선미, 조은채
	 * @Since	: 2020. 3. 26.
	 * @Version	: 1.0
	 * @Desc	: insta_replys 테이블에서 store_no의 댓글 수를 조회한다.
	 * @param store_no
	 */
	public int replyCountByStoreno(String store_no){
		int cnt = 0;
		try {
			InstaReplyMapper mapper = session.getMapper(InstaReplyMapper.class);
			cnt = mapper.replyCountByStoreno(store_no);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
}
