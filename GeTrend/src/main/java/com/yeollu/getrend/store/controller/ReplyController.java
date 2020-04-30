package com.yeollu.getrend.store.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yeollu.getrend.store.dao.InstaReplyDAO;
import com.yeollu.getrend.store.vo.InstaReplyVO;

/**
 * @Class 	: ReplyController.java
 * @Package	: com.yeollu.getrend.store.controller
 * @Project : GeTrend
 * @Author	: 오선미, 조은채
 * @Since	: 2020. 3. 26.
 * @Version	: 1.0
 * @Desc	: 댓글 관련 작업을 제어한다.
 */
@Controller
@RequestMapping(value="/reply")
public class ReplyController {
	
	/**
	 * Fields
	 */
	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	@Autowired
	private InstaReplyDAO dao;
	
	/**
	 * @Method	: replyWrite
	 * @Return	: String
	 * @Author	: 오선미, 조은채
	 * @Since	: 2020. 3. 26.
	 * @Version	: 1.0
	 * @Desc	: 댓글을 등록한다.
	 * @param reply
	 * @param session
	 */
	@RequestMapping(value = "/replyWrite", method = RequestMethod.POST)
	public String replyWrite(InstaReplyVO reply, HttpSession session) {
		logger.info("{}", reply);
		String user_email = (String)session.getAttribute("loginemail");
		logger.info(user_email);
		reply.setUser_email(user_email);
		
		int cnt = dao.replyWrite(reply);
		if(cnt > 0) {
			logger.info("댓글 등록 성공");
		} else {
			logger.info("댓글 등록 실패");
		}
		return "redirect:/stores/istoreInfo?store_no=" + reply.getStore_no();
	}

	
}
