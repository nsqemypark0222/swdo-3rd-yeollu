package com.yeollu.getrend.store.controller;

import java.util.ArrayList;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeollu.getrend.crawler.instagram_location_post;
import com.yeollu.getrend.store.dao.LikeDAO;
import com.yeollu.getrend.store.util.map.core.Polygon;
import com.yeollu.getrend.store.util.map.model.Point;
import com.yeollu.getrend.store.util.preprocess.core.QueryStringSender;
import com.yeollu.getrend.store.vo.LikeVO;

@Controller
@RequestMapping(value = "/likes")
public class LikeController {
	
	private static final Logger logger = LoggerFactory.getLogger(LikeController.class);

	@Autowired
	private LikeDAO likeDAO;
		
	
	@RequestMapping(value = "/likeInsert", method = RequestMethod.POST)
	@ResponseBody
	public void likeInsert(LikeVO like) {
		logger.info("likeInsert");
		int cnt = likeDAO.likeInsert(like);
		logger.info("likeInsert cnt {}",cnt);
	}
	
	@RequestMapping(value = "/likeDelete", method = RequestMethod.POST)
	@ResponseBody
	public void likeDelete(LikeVO like) {
		logger.info("likeDelete");
		int cnt = likeDAO.likeDelete(like);
		logger.info("likeDelete cnt {}",cnt);
	}
	
	@RequestMapping(value = "/likeSelectByEmail", method = RequestMethod.GET)
	public String likeSelectAll(String user_email, Model model) {
		logger.info("likeSelectByEmail");
		logger.info("user_email {}",user_email);
		ArrayList<LikeVO> list = likeDAO.likeSelectByEmail(user_email);
		logger.info("likeSelectByEmail list {}",list);
		model.addAttribute("list", list);
		return "like_test";
	}
	
	
	@RequestMapping(value = "/likeSelectByStoreno", method = RequestMethod.GET)
	public String likeSelectByStoreno(String store_no, Model model) {
		logger.info("likeSelectByStoreno");
		logger.info("store_no {}",store_no);
		ArrayList<LikeVO> list = likeDAO.likeSelectByStoreno(store_no);
		logger.info("likeSelectByStoreno list {}",list);
		model.addAttribute("list", list);
		return "like_test";
	}

}
