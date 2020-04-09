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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeollu.getrend.store.dao.InstaReplyDAO;
import com.yeollu.getrend.store.dao.LikeDAO;
import com.yeollu.getrend.store.dao.StoreDAO;
import com.yeollu.getrend.store.vo.InstaReplyVO;
import com.yeollu.getrend.store.vo.LikeVO;
import com.yeollu.getrend.user.dao.FollowDAO;
import com.yeollu.getrend.user.dao.UserDAO;
import com.yeollu.getrend.user.vo.FollowVO;
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
	
	@RequestMapping(value = "/modalTest", method = RequestMethod.GET)
	public String modalTest(Model model,HttpSession session) {
		return "mypage/follow_modal";
	
	}
	
	@RequestMapping(value = "/mypageForm", method = RequestMethod.GET)
	public String mypageForm(Model model,HttpSession session) {
		//가게 상세페이지
		logger.info("마이페이지 테스트");
		model.addAttribute("store_no", "25523615");
		model.addAttribute("store_name", "스타벅스");
		
		
		//내가 좋아요 누른 가게인지 체크
		LikeVO like = new LikeVO();
		like.setUser_email((String)session.getAttribute("loginemail"));
		like.setStore_no("25523615");//스타벅스
		logger.info("like {}",like);
		int likecheck = likeDAO.likeSelectByEmailStoreno(like);
		logger.info("likecheck {}",likecheck);
		model.addAttribute("likecheck", likecheck);//좋아요 눌렀으면 likecheck = 1
		return "mypage/mypage_test";
	}
	
	//mypage
	//내 페이지
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String mypage(String user_name, HttpSession session, Model model) {
		
		String user_email = (String)session.getAttribute("loginemail");		
		//내 프로필
		if(user_name.equals(userDAO.selectEmail(user_email).getUser_name())) {
			logger.info("내 프로필");
			model.addAttribute("user", userDAO.selectEmail(user_email));
			model.addAttribute("like", likeDAO.likeStoreCountByEmail(user_email));
			model.addAttribute("follow", followDAO.countFollow(user_email));
			model.addAttribute("follower", followDAO.countFollower(user_email));
			ArrayList<HashMap<String, Object>> replyList = replyDAO.replyListByEmail(user_email);
		
			//좋아요한 가게인지 확인
			for (HashMap<String, Object> hashMap : replyList) {
				LikeVO like = new LikeVO();
				like.setStore_no((String)hashMap.get("STORE_NO"));
				like.setUser_email((String)hashMap.get("USER_EMAIL"));
				int likeCheck = likeDAO.likeSelectByEmailStoreno(like);
				if(likeCheck == 1) hashMap.put("LIKE", true);
				else hashMap.put("LIKE", false);
				
			}
			model.addAttribute("replyList",replyList);	
			
		//남 프로필	
		}else {
			logger.info("남 프로필");
			UserVO user = userDAO.selectName(user_name);
			model.addAttribute("user", user);
			model.addAttribute("like", likeDAO.likeStoreCountByEmail(user.getUser_email()));
			model.addAttribute("follow", followDAO.countFollow(user.getUser_email()));
			model.addAttribute("follower", followDAO.countFollower(user.getUser_email()));
			ArrayList<HashMap<String, Object>> replyList = replyDAO.replyListByEmail(user.getUser_email());
			//좋아요한 가게인지 확인
			for (HashMap<String, Object> hashMap : replyList) {
				LikeVO like = new LikeVO();
				like.setStore_no((String)hashMap.get("STORE_NO"));
				like.setUser_email((String)hashMap.get("USER_EMAIL"));
				int likeCheck = likeDAO.likeSelectByEmailStoreno(like);
				if(likeCheck == 1) hashMap.put("LIKE", true);
				else hashMap.put("LIKE", false);
				

			}
			model.addAttribute("replyList",replyList);
			
			//내가 팔로잉 중인 사람인지 체크
			FollowVO follow = new FollowVO();
			follow.setUser_email(user_email);
			follow.setFollows_following(user.getUser_email());
			int ck = followDAO.checkFollow(follow);
			if(ck == 1) {
				model.addAttribute("following", true);
			}else {
				model.addAttribute("following", false);
			}
		}
		return "mypage/mypage";
	}
	
	@RequestMapping(value = "/mypageFriendReply", method = RequestMethod.GET)
	public String mypageFriendReply(String user_name, HttpSession session, Model model) {
		String user_email = (String)session.getAttribute("loginemail");		
		//내 프로필
		if(user_name.equals(userDAO.selectEmail(user_email).getUser_name())) {
			logger.info("내 프로필");
			
			//헤더
			model.addAttribute("user", userDAO.selectEmail(user_email));
			model.addAttribute("like", likeDAO.likeStoreCountByEmail(user_email));
			model.addAttribute("follow", followDAO.countFollow(user_email));
			model.addAttribute("follower", followDAO.countFollower(user_email));
		
			
			//내가 관심있는 팔로우한 친구들
			ArrayList<HashMap<String, Object>> replyList = replyDAO.replyListByFollow(user_email);
			
			//좋아요한 가게인지 확인
			for (HashMap<String, Object> hashMap : replyList) {
				LikeVO like = new LikeVO();
				like.setStore_no((String)hashMap.get("STORE_NO"));
				like.setUser_email((String)hashMap.get("USER_EMAIL"));
				int likeCheck = likeDAO.likeSelectByEmailStoreno(like);
				if(likeCheck == 1) hashMap.put("LIKE", true);
				else hashMap.put("LIKE", false);
			}
			model.addAttribute("replyList",replyList);
			logger.info("replyList {}",replyList);
		//남 프로필	
		}else {
			logger.info("남 프로필");
			//헤더
			UserVO user = userDAO.selectName(user_name);
			model.addAttribute("user", user);
			model.addAttribute("like", likeDAO.likeStoreCountByEmail(user.getUser_email()));
			model.addAttribute("follow", followDAO.countFollow(user.getUser_email()));
			model.addAttribute("follower", followDAO.countFollower(user.getUser_email()));
			ArrayList<HashMap<String, Object>> replyList = replyDAO.replyListByFollow(user.getUser_email());
	
			//리스트
			//좋아요한 가게인지 확인
			for (HashMap<String, Object> hashMap : replyList) {
				LikeVO like = new LikeVO();
				like.setStore_no((String)hashMap.get("STORE_NO"));
				like.setUser_email((String)hashMap.get("USER_EMAIL"));
				int likeCheck = likeDAO.likeSelectByEmailStoreno(like);
				if(likeCheck == 1) hashMap.put("LIKE", true);
				else hashMap.put("LIKE", false);
			}
			model.addAttribute("replyList",replyList);
			logger.info("replyList {}",replyList);
			//내가 팔로잉 중인 사람인지 체크
			FollowVO follow = new FollowVO();
			follow.setUser_email(user_email);
			follow.setFollows_following(user.getUser_email());
			int ck = followDAO.checkFollow(follow);
			if(ck == 1) {
				model.addAttribute("following", true);
			}else {
				model.addAttribute("following", false);
			}
		}
		
		return "mypage/mypage";
	}
	
	@RequestMapping(value = "/mypageLikedStore", method = RequestMethod.GET)
	public String mypageLikedStore(String user_name, HttpSession session, Model model) {
		String user_email = (String)session.getAttribute("loginemail");		
		//내 프로필
		if(user_name.equals(userDAO.selectEmail(user_email).getUser_name())) {
			logger.info("내 프로필");
			model.addAttribute("user", userDAO.selectEmail(user_email));
			model.addAttribute("like", likeDAO.likeStoreCountByEmail(user_email));
			model.addAttribute("follow", followDAO.countFollow(user_email));
			model.addAttribute("follower", followDAO.countFollower(user_email));
			ArrayList<HashMap<String, Object>> replyList = replyDAO.replyListByLikedStore(user_email);
		
			//좋아요한 가게인지 확인
			for (HashMap<String, Object> hashMap : replyList) {
				LikeVO like = new LikeVO();
				like.setStore_no((String)hashMap.get("STORE_NO"));
				like.setUser_email((String)hashMap.get("USER_EMAIL"));
				int likeCheck = likeDAO.likeSelectByEmailStoreno(like);
				if(likeCheck == 1) hashMap.put("LIKE", true);
				else hashMap.put("LIKE", false);
			
			}
			model.addAttribute("replyList",replyList);	
			logger.info("mypageLikedStore replyList {}",replyList);
		//남 프로필	
		}else {
			logger.info("남 프로필");
			UserVO user = userDAO.selectName(user_name);
			model.addAttribute("user", user);
			model.addAttribute("like", likeDAO.likeStoreCountByEmail(user.getUser_email()));
			model.addAttribute("follow", followDAO.countFollow(user.getUser_email()));
			model.addAttribute("follower", followDAO.countFollower(user.getUser_email()));
			ArrayList<HashMap<String, Object>> replyList = replyDAO.replyListByLikedStore(user.getUser_email());
	
			//좋아요한 가게인지 확인
			for (HashMap<String, Object> hashMap : replyList) {
				LikeVO like = new LikeVO();
				like.setStore_no((String)hashMap.get("STORE_NO"));
				like.setUser_email((String)hashMap.get("USER_EMAIL"));
				int likeCheck = likeDAO.likeSelectByEmailStoreno(like);
				if(likeCheck == 1) hashMap.put("LIKE", true);
				else hashMap.put("LIKE", false);
				
			}
			model.addAttribute("replyList",replyList);
			logger.info("replyList {}",replyList);
			//내가 팔로잉 중인 사람인지 체크
			FollowVO follow = new FollowVO();
			follow.setUser_email(user_email);
			follow.setFollows_following(user.getUser_email());
			int ck = followDAO.checkFollow(follow);
			if(ck == 1) {
				model.addAttribute("following", true);
			}else {
				model.addAttribute("following", false);
			}
		}
		
		return "mypage/mypage";
	}
	
	
	
	@RequestMapping(value = "/mypageSession", method = RequestMethod.GET)
	public String mypage(HttpSession session, Model model) {
		
		String user_email = (String)session.getAttribute("loginemail");		
			
		model.addAttribute("user", userDAO.selectEmail(user_email));
		model.addAttribute("like", likeDAO.likeStoreCountByEmail(user_email));
		model.addAttribute("follow", followDAO.countFollow(user_email));
		model.addAttribute("follower", followDAO.countFollower(user_email));
		ArrayList<HashMap<String, Object>> replyList = replyDAO.replyListByEmail(user_email);
		//좋아요한 가게인지 확인
		for (HashMap<String, Object> hashMap : replyList) {
			LikeVO like = new LikeVO();
			like.setStore_no((String)hashMap.get("STORE_NO"));
			like.setUser_email((String)hashMap.get("USER_EMAIL"));
			int likeCheck = likeDAO.likeSelectByEmailStoreno(like);
			if(likeCheck == 1) hashMap.put("LIKE", true);
			else hashMap.put("LIKE", false);
		}		
		model.addAttribute("replyList",replyList);	
	
		return "mypage/mypage";
	}
	

	
	
	//follows_following을 구독하는 사람 리스트
	@RequestMapping(value = "/followerList", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<HashMap<String, Object>> followerList(String follows_following, HttpSession session, Model model) {
		ArrayList<HashMap<String, Object>> list = followDAO.selectFollower(follows_following);
		//내가 팔로잉 중인 사람인지 체크	
		for (HashMap<String, Object> hashMap : list) {
			String loginemail = (String)session.getAttribute("loginemail");			
			FollowVO follow = new FollowVO();
			follow.setUser_email(loginemail);
			follow.setFollows_following((String)hashMap.get("USER_EMAIL"));
			logger.info("팔로우 {}", follow);
			if(follow.getUser_email().equals(follow.getFollows_following())) {
				hashMap.put("FOLLOWING", "me");
			}else {
				int ck = followDAO.checkFollow(follow);		
				if(ck == 1) {
					hashMap.put("FOLLOWING", "yes");
				}else {
					hashMap.put("FOLLOWING", "no");
				}				
			}
		  }//for
		
		logger.info("list {}",list);
		return list;
	}
	
	//user_email이 구독하는 사람 리스트
	@RequestMapping(value = "/followList", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<HashMap<String, Object>> followList(String user_email, HttpSession session, Model model) {
		ArrayList<HashMap<String, Object>> list = followDAO.selectFollowing(user_email);
		//내가 팔로잉 중인 사람인지 체크	
		for (HashMap<String, Object> hashMap : list) {
			String loginemail = (String)session.getAttribute("loginemail");			
			FollowVO follow = new FollowVO();
			follow.setUser_email(loginemail);
			follow.setFollows_following((String)hashMap.get("USER_EMAIL"));
			logger.info("팔로우 {}", follow);
			if(follow.getUser_email().equals(follow.getFollows_following())) {
				hashMap.put("FOLLOWING", "me");
			}else {
				int ck = followDAO.checkFollow(follow);		
				if(ck == 1) {
					hashMap.put("FOLLOWING", "yes");
				}else {
					hashMap.put("FOLLOWING", "no");
				}				
			}
		  }//for
		
		logger.info("list {}",list);
		return list;
	}
	
	//user_email이 구독하는 가게 리스트
	@RequestMapping(value = "/likeStoreList", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<HashMap<String, Object>> likeStoreList(String user_email, HttpSession session, Model model) {
		ArrayList<HashMap<String, Object>> list = likeDAO.likeSelectByEmail(user_email);
		//내가 팔로잉 중인 사람인지 체크	
		for (HashMap<String, Object> hashMap : list) {
			String loginemail = (String)session.getAttribute("loginemail");			
			FollowVO follow = new FollowVO();
			follow.setUser_email(loginemail);
			follow.setFollows_following((String)hashMap.get("USER_EMAIL"));
			logger.info("팔로우 {}", follow);
			if(follow.getUser_email().equals(follow.getFollows_following())) {
				hashMap.put("FOLLOWING", "me");
			}else {
				int ck = followDAO.checkFollow(follow);		
				if(ck == 1) {
					hashMap.put("FOLLOWING", "yes");
				}else {
					hashMap.put("FOLLOWING", "no");
				}				
			}
		  }//for
		
		logger.info("list {}",list);
		return list;
	}
	
}
