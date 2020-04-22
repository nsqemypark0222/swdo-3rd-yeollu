package com.yeollu.getrend.store.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeollu.getrend.store.dao.LikeDAO;
import com.yeollu.getrend.store.vo.LikeVO;

@Controller
@RequestMapping(value = "/likes")
public class LikeController {
	
	private static final Logger logger = LoggerFactory.getLogger(LikeController.class);

	@Autowired
	private LikeDAO likeDAO;
		
	//가게 좋아요
	@RequestMapping(value = "/likeInsert", method = RequestMethod.POST)
	@ResponseBody
	public void likeInsert(LikeVO like, HttpSession session) {
		like.setUser_email((String)session.getAttribute("loginemail"));
		logger.info("likeInsert {}",like);
		
		if(likeDAO.isExistedLike(like)) {
			logger.info("이미 추가됨");
			return;
		} else {
			int cnt = likeDAO.likeInsert(like);
			logger.info("likeInsert cnt {}",cnt);
		}
	}
	
	
	//좋아요 취소
	@RequestMapping(value = "/likeDelete", method = RequestMethod.POST)
	@ResponseBody
	public void likeDelete(LikeVO like,HttpSession session) {
		logger.info("likeDelete");
		like.setUser_email((String)session.getAttribute("loginemail"));
		int cnt = likeDAO.likeDelete(like);
		logger.info("likeDelete cnt {}",cnt);
	}
	


}
