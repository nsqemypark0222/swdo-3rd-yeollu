package com.yeollu.getrend.store.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yeollu.getrend.store.vo.InstaStoreInfoVO;

@Controller
@RequestMapping(value = "/stores")
public class StoreController {
	
	private static final Logger logger = LoggerFactory.getLogger(StoreController.class);
	
	@RequestMapping(value = "/istoreInfo", method = RequestMethod.GET)
	public String istoreInfo(String store_no, HttpSession session, Model model) {
		logger.info("store_no : {}", store_no);
		ArrayList<InstaStoreInfoVO> instaStoreInfoList = (ArrayList<InstaStoreInfoVO>) session.getAttribute("istores");
		for(InstaStoreInfoVO instaStoreInfo : instaStoreInfoList) {
			if(instaStoreInfo.getInstaStore().getStore_no().equals(store_no)) {
				model.addAttribute("istore", instaStoreInfo);
				break;
			}
		}
		return "/stores/istoreInfo";
	}
}
