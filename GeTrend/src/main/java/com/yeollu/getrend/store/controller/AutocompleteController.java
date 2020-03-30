package com.yeollu.getrend.store.controller;

import java.util.ArrayList;
import java.util.Locale;
 
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.yeollu.getrend.store.dao.StoreDAO;
import com.yeollu.getrend.store.vo.StoreVO;

@Controller
@RequestMapping(value="/autocomplete")
public class AutocompleteController {
	@Autowired
	private StoreDAO storeDAO;
	private static final Logger logger = LoggerFactory.getLogger(AutocompleteController.class);

	
	@RequestMapping(value = "/source", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String json01(String param) {    
	    logger.info("param {}", param);
	    ArrayList<String> array = new ArrayList<String>();
		array.addAll(storeDAO.autoStoreName());
		array.addAll(storeDAO.autoStoreAdr());
		array.addAll(storeDAO.autoStoreCate2_01());
		array.addAll(storeDAO.autoStoreCate2_02());
		array.addAll(storeDAO.autoStoreCate2_03());
		
	    ArrayList<String> list = new ArrayList<String>();
	    int j = 0;
		for (int i = 0; i < array.size(); i++) {
	    	if(array.get(i).contains(param)) {
				list.add(array.get(i));
				j++;
				if(j > 10) {
					logger.info("검색 수 {}", i);
					break;
				}
			}
		}
		logger.info("list {}",list);
		
		Gson gson = new Gson();
	    return gson.toJson(list);
	}
		
	@RequestMapping(value = "/keyword", method = RequestMethod.POST)
	@ResponseBody
	public void keyword(String keyword) {    
		logger.info("keyword {}", keyword);
	}
	
	
	
}
