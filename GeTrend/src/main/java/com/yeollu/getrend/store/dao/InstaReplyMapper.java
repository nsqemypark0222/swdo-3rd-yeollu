package com.yeollu.getrend.store.dao;

import java.util.ArrayList;

import com.yeollu.getrend.store.vo.InstaReplyVO;

public interface InstaReplyMapper {

	public int replyWrite(InstaReplyVO reply);
	public ArrayList<InstaReplyVO> replyList(InstaReplyVO reply);
	public int replyRemove(InstaReplyVO vo);
}
