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

/**
 * @Class 	: UserController.java
 * @Package	: com.yeollu.getrend.user.controller
 * @Project : GeTrend
 * @Author	: 오선미
 * @Since	: 2020. 3. 12.
 * @Version	: 1.0
 * @Desc	: 회원 정보 관련 작업을 수행한다.
 */
@Controller
@RequestMapping(value = "/users")
public class UserController {
	
	/**
	 * Fields
	 */
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserDAO dao;
	@Autowired
	private BCryptPasswordEncoder passEncoder;
	@Inject
	private MailService mailService;
	
	/**
	 * @Method	: setMailService
	 * @Return	: void
	 * @Author	: 오선미
	 * @Since	: 2020. 3. 21.
	 * @Version	: 1.0
	 * @Desc	: Setter
	 * @param mailService
	 */
	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}
	
	/**
	 * @Method	: userJoin
	 * @Return	: String
	 * @Author	: 오선미
	 * @Since	: 2020. 3. 12.
	 * @Version	: 1.0
	 * @Desc	: userJoin.jsp로 페이지를 전환한다.
	 */
	@RequestMapping(value="/userJoin", method=RequestMethod.GET)
	public String userJoin() {
		logger.info("회원가입페이지");
		return "/users/userJoin";
	}
	
	/**
	 * @Method	: kakaoshare
	 * @Return	: String
	 * @Author	: 오선미
	 * @Since	: 2020. 3. 24.
	 * @Version	: 1.0
	 * @Desc	: kakaoshare.jsp로 페이지를 전환한다.
	 */
	@RequestMapping(value="/kakaoshare", method=RequestMethod.GET)
	public String kakaoshare() {
		logger.info("카카오 공유하기");
		return "/users/kakaoshare";
	}
	
	/**
	 * @Method	: emailCheck
	 * @Return	: String
	 * @Author	: 오선미
	 * @Since	: 2020. 3. 12.
	 * @Version	: 1.0
	 * @Desc	: 회원가입 중 이메일의 중복을 확인한다.
	 * @param user_email
	 */
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
	
    /**
     * @Method	: sendMailAuth
     * @Return	: String
	 * @Author	: 오선미
	 * @Since	: 2020. 3. 21.
     * @Version	: 1.0
     * @Desc	: 회원가입 중 인증 코드를 이메일로 보낸다.
     * @param session
     * @param user_email
     */
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
    
    /**
     * @Method	: joinCodeCheck
     * @Return	: String
	 * @Author	: 오선미
	 * @Since	: 2020. 3. 21.
     * @Version	: 1.0
     * @Desc	: 회원가입 중 인증 코드를 확인한다.
     * @param session
     * @param joinCode
     */
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
    
	/**
	 * @Method	: userLogin
	 * @Return	: String
	 * @Author	: 오선미
	 * @Since	: 2020. 3. 12.
	 * @Version	: 1.0
	 * @Desc	: 쿠키에 이메일을 저장한 후 userLogin.jsp로 페이지를 전환한다.
	 * @param request
	 * @param model
	 */
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
	
	
	/**
	 * @Method	: join
	 * @Return	: String
	 * @Author	: 오선미
	 * @Since	: 2020. 3. 12.
	 * @Version	: 1.0
	 * @Desc	: 회원 가입을 수행한다.
	 * @param user
	 */
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
	
	/**
	 * @Method	: kakaoLogin
	 * @Return	: String
	 * @Author	: 오선미
	 * @Since	: 2020. 3. 17.
	 * @Version	: 1.0
	 * @Desc	: 카카오 로그인을 수행한다.
	 * @param user
	 * @param session
	 */
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
	
	/**
	 * @Method	: callback
	 * @Return	: String
	 * @Author	: 오선미
	 * @Since	: 2020. 3. 18.
	 * @Version	: 1.0
	 * @Desc	: 네이버 로그인 콜백을 수행하기 위해 callback.jsp로 페이지를 전환한다.
	 * @return
	 */
	@RequestMapping(value = "/callback", method = RequestMethod.GET)
	public String callback() {
		logger.info("callback");
		return "/users/callback";
	}
	
	//네이버로그인
	/**
	 * @Method	: naverLogin
	 * @Return	: String
	 * @Author	: 오선미
	 * @Since	: 2020. 3. 18.
	 * @Version	: 1.0
	 * @Desc	: 네이버 로그인을 수행한다.
	 * @param user
	 * @param session
	 */
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
	/**
	 * @Method	: login
	 * @Return	: String
	 * @Author	: 오선미
	 * @Since	: 2020. 3. 12.
	 * @Version	: 1.0
	 * @Desc	: 로컬 로그인을 수행한다.
	 * @param user
	 * @param remember
	 * @param session
	 * @param response
	 * @param model
	 */
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
	
	/**
	 * @Method	: logout
	 * @Return	: String
	 * @Author	: 오선미
	 * @Since	: 2020. 3. 12.
	 * @Version	: 1.0
	 * @Desc	: 로그아웃을 수행한다.
	 * @param session
	 */
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("loginemail");
		session.removeAttribute("loginname");
		session.removeAttribute("istores");
		logger.info("로그아웃");
		return "redirect:/";
	}
	
	/**
	 * @Method	: userUpdate
	 * @Return	: String
	 * @Author	: 오선미
	 * @Since	: 2020. 3. 12.
	 * @Version	: 1.0
	 * @Desc	: 회원정보 수정을 위해 userUpdate.jsp로 페이지를 전환한다.
	 * @param session
	 * @param model
	 * @return
	 */
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
	
	/**
	 * @Method	: update
	 * @Return	: String
	 * @Author	: 오선미
	 * @Since	: 2020. 3. 12.
	 * @Version	: 1.0
	 * @Desc	: 회원정보를 수정한다.
	 * @param user
	 * @param session
	 * @param userAvatar
	 */
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
	
	/**
	 * @Method	: deleteUser
	 * @Return	: String
	 * @Author	: 오선미
	 * @Since	: 2020. 3. 12.
	 * @Version	: 1.0
	 * @Desc	: 회원 탈퇴를 수행한다.
	 * @param user_email
	 * @param session
	 */
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
}