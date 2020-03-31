package com.yeollu.getrend.user.controller;

import java.util.Random;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeollu.getrend.user.dao.UserDAO;
import com.yeollu.getrend.user.util.MailService;
import com.yeollu.getrend.user.vo.UserVO;
import com.yeollu.getrend.util.PropertiesUtil;

@Controller
@RequestMapping(value = "/users")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserDAO dao;
	
	@Autowired
	BCryptPasswordEncoder passEncoder;
	
	@Inject
	private MailService mailService;
	
	
	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}
	//회원가입 페이지 이동
	@RequestMapping(value="/userJoin", method=RequestMethod.GET)
	public String userJoin() {
		logger.info("회원가입페이지");
		return "/users/userJoin";
	}
	
	@RequestMapping(value="/kakaoshare", method=RequestMethod.GET)
	public String kakaoshare() {
		logger.info("카카오 공유하기");
		return "/users/kakaoshare";
	}
	//회원가입 이메일 중복확인
	@RequestMapping(value="/emailCheck",method=RequestMethod.GET)
	@ResponseBody
	public String emailCheck(String user_email) {
		String result = "1";
		if(dao.selectEmail(user_email) == null) {
			logger.info("email is null");
			result = "0";
		}
		logger.info(result+"");
		return result;
	}
	
	// 회원가입 이메일 인증
    @RequestMapping(value = "/emailAuth", method = RequestMethod.POST)
    @ResponseBody
    public String sendMailAuth(HttpSession session, @RequestParam String user_email) {
        logger.info("dd");
    	int ran = new Random().nextInt(100000) + 10000; // 10000 ~ 99999
        String joinCode = String.valueOf(ran);
        session.setAttribute("joinCode", joinCode);
 
        String subject = "회원가입 인증 코드 발급 안내 입니다.";
        StringBuilder sb = new StringBuilder();
        sb.append("귀하의 인증 코드는 " + joinCode + " 입니다.");
        String from = PropertiesUtil.get("mail", "mail_username");
        boolean result = mailService.send(subject, sb.toString(), from, user_email, null);
        if(result) {
        	return "success";
        }
        else {
        	return "fail";
        }
    }
    //회워가입 인증 코드 확인
    @RequestMapping(value = "/joinCodeCheck", method = RequestMethod.POST)
    @ResponseBody
    public String joinCodeCheck(HttpSession session, @RequestParam String joinCode) {
    	logger.info("authNumCheck");
    	String _joinCode = session.getAttribute("joinCode").toString();
    	
    	logger.info("_joinCode : {}", _joinCode);
    	logger.info("joinCode : {}", joinCode);
    	
    	if(_joinCode.equals(joinCode)) {
    		
        	return "success";
        }
        else {
        	return "fail";
        }
    }
    //로컬 로그인페이지 이동 쿠키저장
	@RequestMapping(value="/userLogin", method=RequestMethod.GET)
	public String userLogin(HttpServletRequest request,Model model) {
		logger.info("로그인페이지");
		Cookie[] cookies = request.getCookies();
		String rememberemail = null;
		if(cookies != null) {
			for(Cookie c : cookies) {
				String name = c.getName(); 
				if(name.equals("remail")) {
					rememberemail = c.getValue();
				}
			}
		}
		model.addAttribute("rememberemail",rememberemail);
		return "/users/userLogin";
	}
	
	
	//회원가입
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(UserVO user) {
		logger.info("회원가입");
		
		String inputPw = user.getUser_pw();
		String pw = passEncoder.encode(inputPw);
		user.setUser_pw(pw);
		logger.info(inputPw);
		logger.info(pw);
		
		user.setUser_type("local");
		int cnt = dao.join(user);
		if(cnt>0) logger.info("가입 성공");
		else logger.info("가입 실패");
		
		return "redirect:/";
	}
	//카카오 로그인
	@RequestMapping(value="/kakaoLogin", method=RequestMethod.POST)
	public String kakaoLogin(UserVO user, HttpSession session) {
		String Kakaoemail = user.getUser_email();
		UserVO Kakaouser = dao.selectEmail(Kakaoemail);
		logger.info("{}",user);
		logger.info("{}",Kakaouser);
		if(Kakaouser == null) {
			user.setUser_type("KAKAO");
			
			String inputPw = user.getUser_pw();
			String pw = passEncoder.encode(inputPw);
			user.setUser_pw(pw);
			logger.info(inputPw);
			logger.info(pw);
			
			int cnt = dao.join(user);
			if(cnt>0) {
				logger.info("가입 성공");
				UserVO joineduser = dao.selectEmail(Kakaoemail);
				session.setAttribute("loginemail",joineduser.getUser_email());
				session.setAttribute("loginname",joineduser.getUser_name());
			}
			else {
				logger.info("가입 실패");
				return "redirect:/users/userLogin";
			}
		} else {
			session.setAttribute("loginemail",Kakaouser.getUser_email());
			session.setAttribute("loginname",Kakaouser.getUser_name());
		}
		return "redirect:/";
	}
	
	//네이버 콜백
	@RequestMapping(value = "/callback", method = RequestMethod.GET)
	public String callback() {
		logger.info("callback가는거");
		return "/users/callback";
	}
	//네이버로그인
	@RequestMapping(value="/naverLogin", method=RequestMethod.POST)
	public String naverLogin(UserVO user, HttpSession session) {
		String Naveremail = user.getUser_email();
		UserVO Naveruser = dao.selectEmail(Naveremail);
		if(Naveruser == null) {
			user.setUser_type("NAVER");
			
			String inputPw = user.getUser_pw();
			String pw = passEncoder.encode(inputPw);
			user.setUser_pw(pw);
			logger.info(inputPw);
			logger.info(pw);
			
			int cnt = dao.join(user);
			if(cnt>0) {
				logger.info("가입 성공");
				UserVO joineduser = dao.selectEmail(Naveremail);
				session.setAttribute("loginemail",joineduser.getUser_email());
				session.setAttribute("loginname",joineduser.getUser_name());
			}
			else {
				logger.info("가입 실패");
				return "redirect:/users/userLogin";
			}
		}else {
			session.setAttribute("loginemail", Naveruser.getUser_email());
			session.setAttribute("loginname",Naveruser.getUser_name());
		}
		
		return "redirect:/";
	}
	//로컬 로그인 
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(UserVO user, String remember, 
			HttpSession session, HttpServletResponse response, Model model) {
		UserVO newUser = dao.selectEmail(user.getUser_email());
		String errMsg1 = "";
		String errMsg2 = "";
		logger.info("{}",user);
		logger.info("{}",newUser);
		if(newUser != null) {
			if(passEncoder.matches(user.getUser_pw(), newUser.getUser_pw())) {
				session.setAttribute("loginemail", newUser.getUser_email());
				session.setAttribute("loginname",newUser.getUser_name());
				if(remember != null && remember.equals("1")) {
					Cookie cookie = new Cookie("remail", user.getUser_email());
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
		logger.info("{}",session.getAttribute("loginemail"));
		logger.info("{}",session.getAttribute("loginname"));
		logger.info("로그인 성공");
		return "redirect:/";
	}
	//로그아웃
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("loginemail");
		session.removeAttribute("loginname");
		logger.info("로그아웃");
		return "redirect:/";
	}
	//회원정보 수정 페이지 이동
	@RequestMapping(value="/userUpdate", method=RequestMethod.GET)
	public String userUpdate(HttpSession session, Model model) {
		String user_email = (String)session.getAttribute("loginemail");
		UserVO user = dao.selectEmail(user_email);
		model.addAttribute("user", user);
		logger.info("회원정보수정 페이지");
		logger.info("user_email {}",user_email);
		return "/users/userUpdate";
	}
	//회원정보 수정
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
	//회원탈퇴 
	@RequestMapping(value="deleteUser", method=RequestMethod.GET)
	public String deleteUser(String user_email, HttpSession session) {
		session.removeAttribute("loginemail");
		session.removeAttribute("loginname");
		int cnt = dao.deleteUser(user_email);
	
		if(cnt> 0) {
			logger.info("삭제 성공");
		}else {
			logger.info("삭제 실패");
		}
		return "redirect:/";
	}
	
}
