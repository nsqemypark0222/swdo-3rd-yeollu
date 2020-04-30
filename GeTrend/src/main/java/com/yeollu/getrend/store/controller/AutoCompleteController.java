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
import com.yeollu.getrend.store.dao.StoreDAO;

/**
 * @Class 	: AutoCompleteController.java
 * @Package	: com.yeollu.getrend.store.controller
 * @Project : GeTrend
 * @Author	: 조은채
 * @Since	: 2020. 3. 29.
 * @Version	: 1.0
 * @Desc	: 검색어 자동완성 기능을 수행한다.
 */
@Controller
@RequestMapping(value="/autoComplete")
public class AutoCompleteController {
	
	/**
	 * Fields
	 */
	private static final Logger logger = LoggerFactory.getLogger(AutoCompleteController.class);
	@Autowired
	private StoreDAO storeDAO;

	/**
	 * @Method	: source
	 * @Return	: String
	 * @Author	: 조은채
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: 입력 받은 문자열을 데이터베이스에 조회해 검색된 목록을 반환한다.
	 * @param param
	 */
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
