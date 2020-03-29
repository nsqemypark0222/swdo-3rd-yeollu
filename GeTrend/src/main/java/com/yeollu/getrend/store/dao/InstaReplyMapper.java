package com.yeollu.getrend.store.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.yeollu.getrend.store.vo.InstaReplyVO;

public interface InstaReplyMapper {

	public int replyWrite(InstaReplyVO reply);
	public int replyRemove(InstaReplyVO vo);
	public ArrayList<HashMap<String, Object>> replyList(InstaReplyVO reply);
}
