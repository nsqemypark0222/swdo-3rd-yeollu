package com.yeollu.getrend.store.controller;

import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.yeollu.getrend.store.dao.InstaLocationDAO;
import com.yeollu.getrend.store.dao.SearchedStoreDAO;
import com.yeollu.getrend.store.dao.StoreDAO;
import com.yeollu.getrend.store.util.json.core.JsonReader;
import com.yeollu.getrend.store.util.json.vo.JsonLocationVO;
import com.yeollu.getrend.store.util.map.core.LocationDistance;
import com.yeollu.getrend.store.util.map.core.Polygon;
import com.yeollu.getrend.store.util.map.model.Point;
import com.yeollu.getrend.store.util.preprocess.core.QueryStringSender;
import com.yeollu.getrend.store.util.preprocess.core.StringPreprocessor;
import com.yeollu.getrend.store.vo.InstaLocationVO;
import com.yeollu.getrend.store.vo.InstaStoreVO;
import com.yeollu.getrend.store.vo.SearchedStoreVO;
import com.yeollu.getrend.store.vo.StoreVO;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private StoreDAO storeDAO;
	
	@Autowired
	private SearchedStoreDAO searchedDAO;
	
	@Autowired
	private InstaLocationDAO instaLocationDAO;
		
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		
		return "home";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public void search(@RequestBody ArrayList<Point> points) {
		logger.info("search");
		long startTime = System.currentTimeMillis();
		
//		logger.info("{}", points);
		ArrayList<StoreVO> list = storeDAO.selectAllStores();
		
		ArrayList<StoreVO> selectedList = new ArrayList<StoreVO>();
				
		if(instaLocationDAO.countInstaLocations() == 0) {
			logger.info("set DB initial datas");
			selectedList = list;
		} else {
			Polygon polygon = new Polygon();
			for(int i = 0; i < points.size(); i++) {
				polygon.addPoint(points.get(i));
			}
			
			for(int i = 0; i < list.size(); i++) {
				if(polygon.isContains(list.get(i).getStore_x(), list.get(i).getStore_y())) {
					selectedList.add(list.get(i));
				}	
			}
			
			for(Iterator<StoreVO> iterator = selectedList.iterator(); iterator.hasNext(); ) {
				StoreVO store = iterator.next();
				if(!searchedDAO.isExistedSearchedStore(store.getStore_name())) {
					SearchedStoreVO searched = new SearchedStoreVO();
					searched.setStore_name(store.getStore_name());
					searchedDAO.insertSearchedStore(searched);	
					logger.info("{}", store.getStore_name());
				} else {
					if(instaLocationDAO.isExistedInstaLocation(store.getStore_no())) {
//						logger.info("insta location is existed");
						iterator.remove();
					} else {
//						logger.info("insta location is not existed");
					}					
				}
			}
		}
		

		for(StoreVO store : selectedList) {
			if(!searchedDAO.isExistedSearchedStore(store.getStore_name())) {
				InstaStoreVO instaStore = QueryStringSender.sendQueryString(store);
				if(instaStore != null) {
					ArrayList<InstaLocationVO> instaLocationList = instaStore.getInsta_locations().get(store.getStore_name());
					logger.info("{}", store.getStore_name());
					if(instaLocationList != null && instaLocationList.size() != 0) {
//						if(instaLocationDAO.insertInstaLocationList(instaLocationList) > 0) {
//							logger.info("insert insta location list success");
//						} else {
//							logger.info("insert insta location list fail");
//						}
						logger.info("{}", instaLocationList.size());
						for(int i = 0; i < instaLocationList.size(); i++) {
							InstaLocationVO instaLocation = instaLocationList.get(i);
							if(!instaLocationDAO.isExistedInstaLocation(instaLocation.getStore_no())) {
								if(instaLocationDAO.insertInstaLocation(instaLocation) > 0) {
									logger.info("insert insta location success");
								} else {
									logger.info("insert insta location fail");
								}
							} else {
								logger.info("insta location is existed");
							}
						}
						logger.info("{}", instaLocationList.size());
					} else {
						logger.info("instaLocationList is null");
					}
					logger.info("{}", instaStore);
				} else {
//					logger.info("instaStore is null");
				}
			}
		}
		
		long endTime = System.currentTimeMillis();
		long diff = (endTime - startTime) / 1000;
		logger.info("걸린 시간 : {}", diff);
		
	}
}
