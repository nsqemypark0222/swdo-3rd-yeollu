package com.yeollu.getrend.user.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeollu.getrend.user.dao.FollowDAO;
import com.yeollu.getrend.user.dao.UserDAO;
import com.yeollu.getrend.user.vo.FollowVO;
import com.yeollu.getrend.user.vo.UserVO;


@Controller
public class FollowController {
	
	private static final Logger logger = LoggerFactory.getLogger(FollowController.class);
	
	@Autowired
	private FollowDAO dao;
	@Autowired
	private UserDAO userDAO;
	@RequestMapping(value = "/users/follow", method = RequestMethod.GET)
	public String follow() {
		return "/users/follow";
	}
	
	//follow
	@RequestMapping(value = "/insertFollow", method = RequestMethod.GET)
	public String follow(FollowVO follow, HttpSession session) {
		logger.info("insertFollow");
		String user_email = (String)session.getAttribute("loginemail");
		logger.info(user_email);
		follow.setUser_email(user_email);
		logger.info("{}",follow);
		int cnt = dao.insertFollow(follow);
		logger.info("{}",cnt);
		UserVO user = userDAO.selectEmail(follow.getFollows_following());
		return "redirect:mypage/mypage?user_name=" + user.getUser_name();
	}
	//follow 취소
	@RequestMapping(value = "/deleteFollow", method = RequestMethod.GET)
	public String deleteFollow(FollowVO follow,HttpSession session) {
		logger.info("deleteFollow");
		String user_email = (String)session.getAttribute("loginemail");
		logger.info(user_email);
		follow.setUser_email(user_email);
		logger.info("{}",follow);
		int cnt = dao.deleteFollow(follow);
		logger.info("{}",cnt);
		UserVO user = userDAO.selectEmail(follow.getFollows_following());
		return "redirect:mypage/mypage?user_name=" + user.getUser_name();
	}
	

	
}
