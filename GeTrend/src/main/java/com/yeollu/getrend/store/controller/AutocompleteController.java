package com.yeollu.getrend.store.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
@RequestMapping(value="/autocomplete")
public class AutocompleteController {
	
	private static final Logger logger = LoggerFactory.getLogger(AutocompleteController.class);

	@RequestMapping(value = "/json", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String json(Locale locale, Model model) {    
	    String[] array = {"김치 볶음밥", "신라면", "진라면", "라볶이", "팥빙수","너구리","삼양라면","안성탕면","불닭볶음면","짜왕","라면사리"};
	    
	        Gson gson = new Gson();

	    return gson.toJson(array);//["김치 볶음밥","신라면","진라면","라볶이","팥빙수","너구리","삼양라면","안성탕면","불닭볶음면","짜왕","라면사리"]
	}

	
	@RequestMapping(value = "/keyword", method = RequestMethod.POST)
	@ResponseBody
	public void keyword(String keyword) {    

		logger.info("keyword {}", keyword);
	}
	
	

}
