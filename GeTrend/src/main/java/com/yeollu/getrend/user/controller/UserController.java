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
import org.springframework.web.multipart.MultipartFile;

import com.yeollu.getrend.user.dao.UserDAO;
import com.yeollu.getrend.user.util.MailService;
import com.yeollu.getrend.user.util.ProfileImageHandler;
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
        logger.info("emailAuth");
    	int ran = new Random().nextInt(100000) + 10000; // 10000 ~ 99999
        String joinCode = String.valueOf(ran);
        session.setAttribute("joinCode", joinCode);
 
        String subject = "[GeTrend] 회원가입 인증 코드 발급 안내 입니다.";
        StringBuilder sb = new StringBuilder();
        sb.append("<div style='text-align:center;'>");
        sb.append("<div style='display:inline-block;'>");
        sb.append("<img src='https://ifh.cc/g/aj4tpl.png' width='400'><br><br>");
        sb.append("<h1 class=\"display-4\">귀하의 인증 코드는 <span color='#FF8A00'>" + joinCode + "</span> 입니다.</h1>");
        sb.append("</div>");
        sb.append("</div>");
        String from = PropertiesUtil.get("mail", "mail_username");
        boolean result = mailService.send(subject, sb.toString(), from, user_email, null);
        if(result) {
        	return "success";
        }
        else {
        	return "fail";
        }
    }
    //회원가입 인증 코드 확인
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
		
		if(dao.isExistedUserName(user.getUser_name())) {
			// 유저 이름 이미 존재
		} else {
			String inputPw = user.getUser_pw();
			String pw = passEncoder.encode(inputPw);
			user.setUser_pw(pw);
			logger.info(inputPw);
			logger.info(pw);
			user.setUser_type("LOCAL");
			int cnt = dao.join(user);
			if(cnt > 0) {
				logger.info("가입 성공");
				return "redirect:/";
			}
			else {
				logger.info("가입 실패");
			}
		}
		return "redirect:/users/userJoin";
	}
	//카카오 로그인
	@RequestMapping(value="/kakaoLogin", method=RequestMethod.POST)
	public String kakaoLogin(UserVO user, HttpSession session) {
		if(dao.isExistedUserName(user.getUser_name())) {
			UserVO _user = dao.selectName(user.getUser_name());
			session.setAttribute("loginemail", _user.getUser_email());
			session.setAttribute("loginname", _user.getUser_name());
			session.setAttribute("loginprofile", _user.getUser_profile());
		} else {
			String Kakaoemail = user.getUser_email();
			UserVO Kakaouser = dao.selectEmail(Kakaoemail);
			logger.info("{}",user);
			logger.info("{}",Kakaouser);
			if(Kakaouser == null) {
				user.setUser_type("KAKAO");
				user.setUser_name(user.getUser_name() + "K");
				
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
					session.setAttribute("loginprofile", joineduser.getUser_profile());
				}
				else {
					logger.info("가입 실패");
					return "redirect:/users/userLogin";
				}
			} else {
				session.setAttribute("loginemail",Kakaouser.getUser_email());
				session.setAttribute("loginname",Kakaouser.getUser_name());
				session.setAttribute("loginprofile", Kakaouser.getUser_profile());
			}
		}
		return "redirect:/";
	}
	
	//네이버 콜백
	@RequestMapping(value = "/callback", method = RequestMethod.GET)
	public String callback() {
		logger.info("callback");
		return "/users/callback";
	}
	//네이버로그인
	@RequestMapping(value="/naverLogin", method=RequestMethod.POST)
	public String naverLogin(UserVO user, HttpSession session) {
		if(dao.isExistedUserName(user.getUser_name())) {
			UserVO _user = dao.selectName(user.getUser_name());
			session.setAttribute("loginemail", _user.getUser_email());
			session.setAttribute("loginname", _user.getUser_name());
			session.setAttribute("loginprofile", _user.getUser_profile());
		} else {
			String Naveremail = user.getUser_email();
			UserVO Naveruser = dao.selectEmail(Naveremail);
			if(Naveruser == null) {
				user.setUser_type("NAVER");
				user.setUser_name(user.getUser_name() + "N");
				
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
					session.setAttribute("loginprofile", joineduser.getUser_profile());
				}
				else {
					logger.info("가입 실패");
					return "redirect:/users/userLogin";
				}
			}else {
				session.setAttribute("loginemail", Naveruser.getUser_email());
				session.setAttribute("loginname",Naveruser.getUser_name());
				session.setAttribute("loginprofile", Naveruser.getUser_profile());
			}
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
				session.setAttribute("loginprofile", newUser.getUser_profile());
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
		session.removeAttribute("istores");
		logger.info("로그아웃");
		return "redirect:/";
	}
	//회원정보 수정 페이지 이동
	@RequestMapping(value="/userUpdate", method=RequestMethod.GET)
	public String userUpdate(HttpSession session, Model model) {
		logger.info("회원정보수정 페이지");
		String user_email = (String)session.getAttribute("loginemail");
		UserVO user = dao.selectEmail(user_email);
		model.addAttribute("user", user);
		logger.info("{}",user);
		logger.info("user_email {}",user_email);
		return "/users/userUpdate";
	}
	//회원정보 수정
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(UserVO user, HttpSession session, MultipartFile userAvatar){
		logger.info("회원정보 수정 시작");
		if(!user.getUser_name().equals(session.getAttribute("loginname")) && dao.isExistedUserName(user.getUser_name())) {
			logger.info("수정실패");
			return "redirect:/users/userUpdate";
		}
		
		
		if(!userAvatar.isEmpty()) {
			logger.info("업로드할 유저 아바타 있음");
			UserVO _user = dao.selectEmail(user.getUser_email());
			if(!(_user.getUser_profile() == null || _user.getUser_profile().equals(""))) {
				ProfileImageHandler profileImageHandler = new ProfileImageHandler();
				if(profileImageHandler.delete(_user.getUser_profileId())) {
					logger.info("기존 이미지 삭제 성공");
				} else {
					logger.info("기존 이미지 삭제 실패");
				}
			}
			logger.info("{}", userAvatar.getOriginalFilename());
			ProfileImageHandler profileImageHandler = new ProfileImageHandler();
			if(profileImageHandler.upload(userAvatar)) {
				logger.info("이미지 추가 성공");
				String user_profileId = profileImageHandler.getPublicId();
				String user_profile = profileImageHandler.getUrl();
				logger.info("user_profileId : {}", user_profileId);
				logger.info("user_profile : {}", user_profile);
				user.setUser_profileId(user_profileId);
				user.setUser_profile(user_profile);
			} else {
				logger.info("이미지 추가 실패");
			}
		} else {
			logger.info("업로드할 유저 아바타 없음");
			user.setUser_profile(dao.selectEmail(user.getUser_email()).getUser_profile());
		}
		logger.info("{}", user);
		
		
		int cnt = 0;
		if(user.getUser_type().equals("LOCAL")) {
			String inputPw = user.getUser_pw();
			String pw = passEncoder.encode(inputPw);
			user.setUser_pw(pw);
			logger.info(inputPw);
			logger.info(pw);
			cnt = dao.updateUser(user);
		} else {
			cnt = dao.updateSocialUser(user);
		}
		
		logger.info("{}",cnt);
		if(cnt>0) {
			logger.info("수정성공");
			session.setAttribute("loginname", user.getUser_name());
			session.setAttribute("loginprofile", user.getUser_profile());
		} else {
			logger.info("수정실패");
			return "redirect:/users/userUpdate";
		}
		return "redirect:/";
	}
	
	//회원탈퇴 
	@RequestMapping(value="deleteUser", method=RequestMethod.GET)
	public String deleteUser(String user_email, HttpSession session) {
		
		session.removeAttribute("loginemail");
		session.removeAttribute("loginname");
		session.removeAttribute("loginprofile");
		
		UserVO user = dao.selectEmail(user_email);
		String publicId = user.getUser_profileId();
		
		int cnt = dao.deleteUser(user_email);
	
		if(cnt> 0) {
			logger.info("삭제 성공");
			ProfileImageHandler profileImageHandler = new ProfileImageHandler();
			if(profileImageHandler.delete(publicId)) {
				logger.info("이미지 파일 삭제 성공");
			}
		}else {
			logger.info("삭제 실패");
		}
		return "redirect:/";
	}
	
		//인스타 스토어 이동 테스트 
		@RequestMapping(value="/istore_test", method=RequestMethod.GET)
		public String istore_test() {
			logger.info("인스타 스토어 이동 테스트 ");
			return "/users/istore_test";
		}
}