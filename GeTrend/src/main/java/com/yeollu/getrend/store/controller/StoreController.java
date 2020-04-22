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

import com.yeollu.getrend.store.dao.InstaImageDAO;
import com.yeollu.getrend.store.dao.InstaReplyDAO;
import com.yeollu.getrend.store.dao.LikeDAO;
import com.yeollu.getrend.store.dao.MangoStoreInfoDAO;
import com.yeollu.getrend.store.dao.ScoreDAO;
import com.yeollu.getrend.store.dao.StoreDAO;
import com.yeollu.getrend.store.vo.InstaImageVO;
import com.yeollu.getrend.store.vo.InstaReplyVO;
import com.yeollu.getrend.store.vo.InstaStoreInfoVO;
import com.yeollu.getrend.store.vo.InstaStoreVO;
import com.yeollu.getrend.store.vo.LikeVO;
import com.yeollu.getrend.store.vo.MangoStoreInfoVO;
import com.yeollu.getrend.store.vo.ScoreVO;

@Controller
@RequestMapping(value = "/stores")
public class StoreController {
	
	private static final Logger logger = LoggerFactory.getLogger(StoreController.class);
	
	@Autowired
	private InstaReplyDAO replyDAO;
	
	@Autowired
	private LikeDAO likeDAO;
	
	@Autowired
	private StoreDAO storeDAO;
	
	@Autowired
	private MangoStoreInfoDAO mangoStoreInfoDAO;
	
	@Autowired
	private ScoreDAO scoreDAO;
	
<<<<<<< HEAD
	//상세 페이지
=======
	@Autowired
	private InstaImageDAO instaImageDAO;
	
>>>>>>> 7c95c6552248688866edd3ce4c1efe2bc4f034c0
	@RequestMapping(value = "/istoreInfo", method = RequestMethod.GET)
	public String istoreInfo(String store_no, HttpSession session, Model model) {
		logger.info("store_no : {}", store_no);
		
		InstaStoreVO instaStore = storeDAO.selectInstaStore(store_no);
		
		ArrayList<InstaImageVO> instaImageList = instaImageDAO.selectInstaImageByStoreNo(store_no);
		
		MangoStoreInfoVO mangoStoreInfo = mangoStoreInfoDAO.selectMangoStoreInfoByStoreNo(store_no);
		
		ScoreVO score = scoreDAO.selectScoreByStoreNo(store_no);
		
		InstaStoreInfoVO instaStoreInfo = new InstaStoreInfoVO();
		instaStoreInfo.setInstaStore(instaStore);
		instaStoreInfo.setInstaImageList(instaImageList);
		instaStoreInfo.setMangoStoreInfo(mangoStoreInfo);
		instaStoreInfo.setScore(score);
		
		model.addAttribute("istore", instaStoreInfo);
		

        //댓글 리스트
	    String loginemail = (String)session.getAttribute("loginemail");
	    InstaReplyVO reply = new InstaReplyVO();
		reply.setStore_no(store_no);
		reply.setUser_email(loginemail);
		ArrayList<HashMap<String,Object>> replyList = replyDAO.replyList(reply);
		logger.info("replyList {}",replyList);
		model.addAttribute("replyList", replyList);
		int replyCount = replyDAO.replyCountByStoreno(store_no);
		model.addAttribute("replyCount", replyCount);
		
		//좋아요 눌렀나
		LikeVO like =  new LikeVO();
		like.setStore_no(store_no);
		like.setUser_email(loginemail);
		boolean isExistedLike = likeDAO.isExistedLike(like);
		model.addAttribute("isExistedLike", isExistedLike);
		
		//평균 별점
		int scoreAvg = scoreDAO.scoreAvgByStoreno(store_no);
		model.addAttribute("scoreAvg", scoreAvg);
		

		return "/stores/istoreInfo";
	}
	
	//댓글 작성 jsp
	@RequestMapping(value="istoreinfo_reply", method=RequestMethod.GET)
	public String istoreinfo_reply(String store_name, String store_no, Model model) {
		model.addAttribute("store_name", store_name);
		model.addAttribute("store_no",store_no);
		return "stores/istoreInfo_reply";
	}
	
	//댓글삭제
	@RequestMapping(value = "/deleteReply", method = RequestMethod.GET)
	public String deleteReply(InstaReplyVO reply) {
		logger.info("{}", reply);
		int cnt = replyDAO.replyRemove(reply);
		if(cnt > 0) {
			logger.info("댓글 삭제 성공");
		} else {
			logger.info("댓글 삭제 실패");
		}
		
		return "redirect:istoreInfo?store_no=" + reply.getStore_no();
	}
	
}
