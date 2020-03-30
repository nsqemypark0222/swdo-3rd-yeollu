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

	@RequestMapping(value = "/json01", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String json01(String param) {    
	    logger.info("param {}", param);
		String[] array = {"김치 볶음밥", "신라면", "진라면", "라볶이", "팥빙수","너구리","삼양라면","안성탕면","불닭볶음면","짜왕","라면사리"};	    
	    ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < array.length; i++) {
	    	if(array[i].contains(param)) {
				list.add(array[i]);
			}
		}
		logger.info("list {}",list);
		
		Gson gson = new Gson();
	    return gson.toJson(list);
	}
	
	
	//초성 중성 종성
	@RequestMapping(value = "/json02", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String json02() {    
		ArrayList<String> array = new ArrayList<String>();
		array.addAll(storeDAO.autoStoreName());
		array.addAll(storeDAO.autoStoreAdr1_01());	
		array.addAll(storeDAO.autoStoreAdr1_02());	
		array.addAll(storeDAO.autoStoreCate3_01());
		array.addAll(storeDAO.autoStoreCate3_02());
		array.addAll(storeDAO.autoStoreCate3_03());
		
		Gson gson = new Gson();
	    return gson.toJson(array);
	}
	
		
	@RequestMapping(value = "/keyword", method = RequestMethod.POST)
	@ResponseBody
	public void keyword(String keyword) {    
		logger.info("keyword {}", keyword);
	}
	
	
	
}
