package com.yeollu.getrend.store.controller;

import java.util.ArrayList;

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
	
	@RequestMapping(value = "/replyForm", method = RequestMethod.GET)
	public String replyForm(HttpSession session) {
		
		return "/reply/replyForm";
	}
	
	//등록
	@RequestMapping(value = "replyWrite", method = RequestMethod.GET)
	@ResponseBody
	public void replyWrite(InstaReplyVO reply, HttpSession session) {
		logger.info("{}", reply);
		String user_email = (String)session.getAttribute("loginemail");
		logger.info(user_email);
		reply.setUser_email(user_email);
		dao.replyWrite(reply);
	}
	
	//삭제
	@RequestMapping(value = "replyRemove", method = RequestMethod.GET)
	@ResponseBody
	public void replyRemove(InstaReplyVO reply, HttpSession session) {
		String user_email = (String)session.getAttribute("user_email");
		reply.setUser_email(user_email);
		dao.replyRemove(reply);
	}
	
	//리스트
	@RequestMapping(value = "replyList", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<InstaReplyVO> list(InstaReplyVO reply, HttpSession session) {
		ArrayList<InstaReplyVO> list = dao.replyList(reply);
		return list;
	}
}
