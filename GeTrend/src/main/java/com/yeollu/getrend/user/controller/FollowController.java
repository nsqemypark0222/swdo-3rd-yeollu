package com.yeollu.getrend.user.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yeollu.getrend.user.dao.FollowDAO;
import com.yeollu.getrend.user.dao.UserDAO;
import com.yeollu.getrend.user.vo.FollowVO;
import com.yeollu.getrend.user.vo.UserVO;


/**
 * @Class 	: FollowController.java
 * @Package	: com.yeollu.getrend.user.controller
 * @Project : GeTrend
 * @Author	: 오선미, 조은채
 * @Since	: 2020. 3. 31.
 * @Version	: 1.0
 * @Desc	: 팔로우 관련 작업을 수행한다.
 */
@Controller
public class FollowController {
	
	/**
	 * Fields
	 */
	private static final Logger logger = LoggerFactory.getLogger(FollowController.class);
	@Autowired
	private FollowDAO dao;
	@Autowired
	private UserDAO userDAO;
	
	/**
	 * @Method	: follow
	 * @Return	: String
	 * @Author	: 오선미, 조은채
	 * @Since	: 2020. 3. 31.
	 * @Version	: 1.0
	 * @Desc	: follow.jsp로 페이지를 전환한다.
	 */
	@RequestMapping(value = "/users/follow", method = RequestMethod.GET)
	public String getFollow() {
		return "/users/follow";
	}
	
	/**
	 * @Method	: follow
	 * @Return	: String
	 * @Author	: 오선미, 조은채
	 * @Since	: 2020. 3. 31.
	 * @Version	: 1.0
	 * @Desc	: 팔로우를 추가한다.
	 * @param follow
	 * @param session
	 */
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
		logger.info("{}", user);
		String _user_name = "";
		try {
			_user_name = URLEncoder.encode(user.getUser_name(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			_user_name = userDAO.selectEmail(user_email).getUser_name();
		}

		return "redirect:mypage/mypage?user_name=" + _user_name;
	}
	
	/**
	 * @Method	: deleteFollow
	 * @Return	: String
	 * @Author	: 오선미, 조은채
	 * @Since	: 2020. 3. 31.
	 * @Version	: 1.0
	 * @Desc	: 팔로우를 취소한다.
	 * @param follow
	 * @param session
	 */
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
		logger.info("{}", user);
		String _user_name = "";
		
		try {
			_user_name = URLEncoder.encode(user.getUser_name(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			_user_name = userDAO.selectEmail(user_email).getUser_name();
		}

		return "redirect:mypage/mypage?user_name=" + _user_name;
	}
}
