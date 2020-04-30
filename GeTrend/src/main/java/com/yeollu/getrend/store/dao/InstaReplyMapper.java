package com.yeollu.getrend.store.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.yeollu.getrend.store.vo.InstaReplyVO;

/**
 * @Class 	: InstaReplyMapper.java
 * @Package	: com.yeollu.getrend.store.dao
 * @Project : GeTrend
 * @Author	: 오선미, 조은채
 * @Since	: 2020. 3. 26.
 * @Version	: 1.0
 * @Desc	: insta_replys 테이블의 Mapper interface 역할을 수행한다
 */
public interface InstaReplyMapper {
	public int replyWrite(InstaReplyVO reply);
	public int replyRemove(InstaReplyVO vo);
	public int replyCountByStoreno(String store_no);
	public ArrayList<HashMap<String, Object>> replyList(InstaReplyVO reply);
	public ArrayList<HashMap<String, Object>> replyListByEmail(String user_email);
	public ArrayList<HashMap<String, Object>> replyListByFollow(String user_email);
	public ArrayList<HashMap<String, Object>> replyListByLikedStore(String user_email);
}
