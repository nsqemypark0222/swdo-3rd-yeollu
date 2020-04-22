package com.yeollu.getrend.store.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.yeollu.getrend.crawler.CrawlerExecutor;
import com.yeollu.getrend.mango.dao.MangoStoreDAO;
import com.yeollu.getrend.mango.vo.MangoStoreVO;
import com.yeollu.getrend.store.dao.InstaLocationDAO;
import com.yeollu.getrend.store.dao.StoreDAO;
import com.yeollu.getrend.store.util.preprocess.core.QueryStringSender;
import com.yeollu.getrend.store.vo.InstaImageVO;
import com.yeollu.getrend.store.vo.InstaLocationVO;
import com.yeollu.getrend.store.vo.InstaStoreInfoVO;
import com.yeollu.getrend.store.vo.InstaStoreVO;
import com.yeollu.getrend.store.vo.MangoStoreInfoVO;
import com.yeollu.getrend.store.vo.StoreVO;

@Controller
@RequestMapping(value="/autocomplete")
public class AutocompleteController {
	
	private static final Logger logger = LoggerFactory.getLogger(AutocompleteController.class);
	
	@Autowired
	private StoreDAO storeDAO;
	
	@Autowired
	private InstaLocationDAO instaLocationDAO;
	
	@Autowired
	private MangoStoreDAO mangoStoreDAO;
	

	@RequestMapping(value = "/autocompleteForm", method = RequestMethod.GET)
	public String autocompleteForm() {
		return "autocomplete_test";
	}
	@RequestMapping(value = "/source", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String source(String param) {    
	    logger.info("param {}", param);
	    ArrayList<String> array = new ArrayList<String>();
		array.addAll(storeDAO.autoStoreCate2_01());
		array.addAll(storeDAO.autoStoreCate2_02());
		array.addAll(storeDAO.autoStoreCate2_03());
		array.addAll(storeDAO.autoStoreAdr());
		array.addAll(storeDAO.autoStoreName());
		
	    ArrayList<String> list = new ArrayList<String>();
	    int j = 0;
		for (int i = 0; i < array.size(); i++) {
	    	if(array.get(i).contains(param)) {
				j++;
				if(j > 10) {
					logger.info("검색 수 {}", i);
					break;
				}
				list.add(array.get(i));
			}
		}
		logger.info("list {}",list);
		
		Gson gson = new Gson();
	    return gson.toJson(list);
	}
	
}
