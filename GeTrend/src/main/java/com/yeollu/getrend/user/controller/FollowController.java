package com.yeollu.getrend.user.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeollu.getrend.user.dao.FollowDAO;
import com.yeollu.getrend.user.vo.FollowVO;


@Controller
public class FollowController {
	
	private static final Logger logger = LoggerFactory.getLogger(FollowController.class);
	
	@Autowired
	private FollowDAO dao;
	
	@RequestMapping(value = "/users/follow", method = RequestMethod.GET)
	public String follow() {
		return "/users/follow";
	}
	
	//follow
	@RequestMapping(value = "/insertFollow", method = RequestMethod.POST)
	@ResponseBody
	public void follow(FollowVO follow, HttpSession session) {
		logger.info("insertFollow");
		String user_email = (String)session.getAttribute("loginemail");
		logger.info(user_email);
		follow.setUser_email(user_email);
		logger.info("{}",follow);
		int cnt = dao.insertFollow02(follow);
		logger.info("{}",cnt);
	}
	//follow 취소
	@RequestMapping(value = "/deleteFollow", method = RequestMethod.POST)
	@ResponseBody
	public void deleteFollow(FollowVO follow,HttpSession session) {
		logger.info("deleteFollow");
		String user_email = (String)session.getAttribute("loginemail");
		logger.info(user_email);
		follow.setUser_email(user_email);
		logger.info("{}",follow);
		int cnt = dao.deleteFollow02(follow);
		logger.info("{}",cnt);
	}
	
}
