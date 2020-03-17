package com.yeollu.getrend.user.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeollu.getrend.user.dao.UserDAO;
import com.yeollu.getrend.user.vo.UserVO;

@Controller
@RequestMapping(value = "/users")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserDAO dao;
	
	@RequestMapping(value="/userJoin", method=RequestMethod.GET)
	public String userJoin() {
		logger.info("회원가입페이지");
		return "/users/userJoin";
	}
	
	@RequestMapping(value="/userLogin", method=RequestMethod.GET)
	public String userLogin(HttpServletRequest request,Model model) {
		logger.info("로그인페이지");
		Cookie[] cookies = request.getCookies();
		String rememberId = null;
		if(cookies != null) {
			for(Cookie c : cookies) {
				String name = c.getName(); 
				if(name.equals("rId")) {
					rememberId = c.getValue();
				}
			}
		}
		model.addAttribute("rememberId",rememberId);
		return "/users/userLogin";
	}
	
	
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(UserVO user) {
		int cnt = dao.join(user);
		if(cnt>0) logger.info("가입 성공");
		else logger.info("가입 실패");
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(UserVO user, String remember, 
			HttpSession session, HttpServletResponse response, Model model) {
		UserVO newUser = dao.selectOne(user.getUser_id());
		String errMsg1 = "";
		String errMsg2 = "";
		if(newUser != null) {
			if(user.getUser_pw().equals(newUser.getUser_pw())) {
				session.setAttribute("loginId", user.getUser_id());
				if(remember != null && remember.equals("1")) {
					Cookie cookie = new Cookie("rId", user.getUser_id());
					cookie.setMaxAge(60*60*24*7);
					response.addCookie(cookie);
				}
			}else {
				errMsg2 = "비밀번호가 틀렸습니다.";
				model.addAttribute("errMsg2", errMsg2);
				return "/users/userLogin";
			}
		}else {
			errMsg1 = "아이디가 틀렸습니다.";
			model.addAttribute("errMsg1", errMsg1);
			return "/users/userLogin";
		}
		logger.info("{}",session.getAttribute("loginId"));
		logger.info("로그인 성공");
		return "redirect:/";
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("loginId");
		logger.info("로그아웃");
		return "redirect:/";
	}
	
	@RequestMapping(value="/userUpdate", method=RequestMethod.GET)
	public String userUpdate(HttpSession session, Model model) {
		String user_id = (String)session.getAttribute("loginId");
		UserVO user = dao.selectOne(user_id);
		model.addAttribute("user", user);
		logger.info("user_id {}",user_id);
		return "/users/userUpdate";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(UserVO user){
		logger.info("{}", user);
		int cnt = dao.updateUser(user);
		logger.info("{}",cnt);
		if(cnt>0) {
			logger.info("수정성공");
		}else {
			logger.info("수정실패");
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="deleteUser", method=RequestMethod.GET)
	public String deleteUser(String user_id, HttpSession session) {
		session.removeAttribute("loginId");
		int cnt = dao.deleteUser(user_id);
	
		if(cnt> 0) {
			logger.info("삭제 성공");
		}else {
			logger.info("삭제 실패");
		}
		return "redirect:/";
	}
	
}
