package com.yeollu.getrend.store.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.store.vo.InstaReplyVO;

@Repository
public class InstaReplyDAO {
	
	@Autowired
	private SqlSession session;
	
	public int replyWrite(InstaReplyVO reply) {
		int cnt = 0;
		try {
			InstaReplyMapper mapper = session.getMapper(InstaReplyMapper.class);
			cnt = mapper.replyWrite(reply);
		} catch (Exception e) {
			e.printStackTrace();
		}return cnt;
	}
	

	public int replyRemove(InstaReplyVO reply) {
		int cnt = 0;
		try {
			InstaReplyMapper mapper = session.getMapper(InstaReplyMapper.class);
			cnt = mapper.replyRemove(reply);
		} catch (Exception e) {
			e.printStackTrace();
		}return cnt;
	}
	
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
