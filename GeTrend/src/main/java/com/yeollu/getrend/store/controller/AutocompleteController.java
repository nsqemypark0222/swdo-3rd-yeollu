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
	public String source() {    
		ArrayList<String> array = new ArrayList<String>();
		array.addAll(storeDAO.autoStoreName());
		array.addAll(storeDAO.autoStoreAdr());
		array.addAll(storeDAO.autoStoreCate2_01());
		array.addAll(storeDAO.autoStoreCate2_02());
		array.addAll(storeDAO.autoStoreCate2_03());
		
		Gson gson = new Gson();
	    return gson.toJson(array);
	}
	
		
	@RequestMapping(value = "/keyword", method = RequestMethod.POST)
	@ResponseBody
	public void keyword(String keyword) {    
		logger.info("keyword {}", keyword);
	}
	
	
	
}
