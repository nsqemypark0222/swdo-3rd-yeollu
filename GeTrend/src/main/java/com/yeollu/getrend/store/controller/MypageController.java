package com.yeollu.getrend.store.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeollu.getrend.store.dao.InstaReplyDAO;
import com.yeollu.getrend.store.dao.LikeDAO;
import com.yeollu.getrend.store.dao.StoreDAO;
import com.yeollu.getrend.store.vo.InstaReplyVO;
import com.yeollu.getrend.user.dao.FollowDAO;
import com.yeollu.getrend.user.dao.UserDAO;
import com.yeollu.getrend.user.vo.UserVO;

@Controller
@RequestMapping(value="/mypage")
public class MypageController {
	private static final Logger logger = LoggerFactory.getLogger(MypageController.class);
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private StoreDAO storeDAO;
	@Autowired
	private InstaReplyDAO replyDAO;
	@Autowired
	private LikeDAO likeDAO;
	@Autowired
	private FollowDAO followDAO;
	@RequestMapping(value = "/mypageForm", method = RequestMethod.GET)
	public String mypageForm(Model model) {
		//가상 상세페이지
		//가게 이름 공차, 가게 번호 23248679 , 댓글
		
		
		return "mypage/mypage_test";
	}
	
	//mypage
	//내 페이지
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String mypage(String user_name, HttpSession session, Model model) {
		
		String user_email = (String)session.getAttribute("loginemail");
		
		//내 프로필
		if(user_name.equals(userDAO.selectEmail(user_email).getUser_name())) {
			
			model.addAttribute("user", userDAO.selectEmail(user_email));
			model.addAttribute("like", likeDAO.likeStoreCountByEmail(user_email));
			model.addAttribute("follow", followDAO.countFollow(user_email));
			model.addAttribute("follower", followDAO.countFollower(user_email));
			ArrayList<HashMap<String, Object>> replyList = replyDAO.replyListByEmail(user_email);
			model.addAttribute("replyList",replyList);
	
		//남 프로필	
		}else {
			UserVO user = userDAO.selectName(user_name);
			model.addAttribute("user", user);
			model.addAttribute("like", likeDAO.likeStoreCountByEmail(user.getUser_email()));
			model.addAttribute("follow", followDAO.countFollow(user.getUser_email()));
			model.addAttribute("follower", followDAO.countFollower(user.getUser_email()));
			ArrayList<HashMap<String, Object>> replyList = replyDAO.replyListByEmail(user.getUser_email());
			model.addAttribute("replyList",replyList);
		}
		
		return "mypage/mypage";
	}
	

	
	
}
