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

import com.yeollu.getrend.mango.vo.MangoStoreVO;
import com.yeollu.getrend.store.dao.InstaImageDAO;
import com.yeollu.getrend.store.dao.InstaLocationDAO;
import com.yeollu.getrend.store.dao.MangoStoreInfoDAO;
import com.yeollu.getrend.store.dao.ScoreDAO;
import com.yeollu.getrend.store.dao.StoreDAO;
import com.yeollu.getrend.store.service.StoreService;
import com.yeollu.getrend.store.vo.InstaImageVO;
import com.yeollu.getrend.store.vo.InstaStoreInfoVO;
import com.yeollu.getrend.store.vo.InstaStoreVO;
import com.yeollu.getrend.store.vo.MangoStoreInfoVO;
import com.yeollu.getrend.store.vo.ScoreVO;

@Controller
@RequestMapping(value = "/stores")
public class StoreController {
	
	private static final Logger logger = LoggerFactory.getLogger(StoreController.class);
	
	@Autowired
	private StoreDAO storeDAO;
	
	@Autowired
	private MangoStoreInfoDAO mangoStoreInfoDAO;
	
	@Autowired
	private ScoreDAO scoreDAO;
	
	@Autowired
	private InstaImageDAO instaImageDAO;
	
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
		
		return "/stores/istoreInfo";
	}
}
