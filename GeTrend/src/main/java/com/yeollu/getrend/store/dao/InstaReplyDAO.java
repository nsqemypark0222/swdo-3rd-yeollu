package com.yeollu.getrend.store.dao;

import java.util.ArrayList;

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
	
	public ArrayList<InstaReplyVO> replyList(InstaReplyVO reply) {
		InstaReplyMapper mapper = session.getMapper(InstaReplyMapper.class);
		return mapper.replyList(reply);
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
}
