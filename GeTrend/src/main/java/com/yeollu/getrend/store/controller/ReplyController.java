package com.yeollu.getrend.store.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeollu.getrend.store.dao.InstaReplyDAO;
import com.yeollu.getrend.store.vo.InstaReplyVO;

@Controller
@RequestMapping(value="/reply")
public class ReplyController {
	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	
	@Autowired
	private InstaReplyDAO dao;
	
	//댓글 등록
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
