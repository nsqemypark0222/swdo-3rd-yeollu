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
import com.yeollu.getrend.dataPreprocessing.core.Preprocessor;
import com.yeollu.getrend.json.core.JsonLocationVO;
import com.yeollu.getrend.json.core.JsonReader;
import com.yeollu.getrend.json.core.JsonUserVO;
import com.yeollu.getrend.map.core.Point;
import com.yeollu.getrend.map.core.Polygon;
import com.yeollu.getrend.store.dao.InstaLocationDAO;
import com.yeollu.getrend.store.dao.InstaUserDAO;
import com.yeollu.getrend.store.dao.StoreDAO;
import com.yeollu.getrend.store.vo.InstaStoreVO;
import com.yeollu.getrend.store.vo.InstaLocationInfoVO;
import com.yeollu.getrend.store.vo.InstaLocationVO;
import com.yeollu.getrend.store.vo.InstaUserInfoVO;
import com.yeollu.getrend.store.vo.InstaUserVO;
import com.yeollu.getrend.store.vo.StoreVO;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	
	@Autowired
	private StoreDAO storeDAO;
	
	@Autowired
	private InstaUserDAO instaUserDAO;
	
	@Autowired
	private InstaLocationDAO instaLocationDAO; 
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		

		return "home";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public void search(@RequestBody ArrayList<Point> points) {
		logger.info("search");
		long startTime = System.currentTimeMillis();
		
//		logger.info("{}", points);
		ArrayList<StoreVO> list = storeDAO.selectAllStores();
		
		Polygon polygon = new Polygon();
		for(int i = 0; i < points.size(); i++) {
			polygon.addPoint(points.get(i));
		}
		
		ArrayList<StoreVO> selectedList = new ArrayList<StoreVO>();
		
		for(int i = 0 ; i < list.size(); i++) {
			if(polygon.isContains(list.get(i).getStore_x(), list.get(i).getStore_y())) {
				selectedList.add(list.get(i));
			}	
		}
		for(Iterator<StoreVO> iterator = selectedList.iterator(); iterator.hasNext(); ) {
			if(instaUserDAO.isExistedInstaUserId(iterator.next().getStore_no())) {
				iterator.remove();
			}
		}
		for(Iterator<StoreVO> iterator = selectedList.iterator(); iterator.hasNext(); ) {
			if(instaLocationDAO.isExistedInstaLocationId(iterator.next().getStore_no())) {
				iterator.remove();
			}
		}
		logger.info("{}", selectedList.size());

		
		for(StoreVO store : selectedList) {
			InstaStoreVO instaStore = sendQueryString(store);
			
			if(instaStore != null) {
				String store_no = instaStore.getStore_no();
				ArrayList<InstaUserInfoVO> instaUserInfoList = instaStore.getInsta_users().get(store.getStore_name());
				for(int i = 0; i < instaUserInfoList.size(); i++) {
					InstaUserVO instaUser = new InstaUserVO();
					instaUser.setStore_no(store_no);
					instaUser.setInsta_id(instaUserInfoList.get(i).getInsta_id());
					if(instaUserDAO.insertInstaUser(instaUser) > 0) {
						logger.info("insert insta user success");
					} else {
						logger.info("insert insta user fail");
					}
				}
				
				ArrayList<InstaLocationInfoVO> instaLocationInfoList = instaStore.getInsta_locations().get(store.getStore_name());
				for(int i = 0; i < instaLocationInfoList.size(); i++) {
					InstaLocationVO instaLocation = new InstaLocationVO();
					instaLocation.setStore_no(store_no);
					instaLocation.setLocation_id(instaLocationInfoList.get(i).getLocation_id());
					
					if(instaLocationDAO.insertInstaLocation(instaLocation) > 0) {
						logger.info("insert insta location success");
					} else {
						logger.info("insert insta location fail");
					}
				}
				
				logger.info("{}", instaStore);
			}
		}
		

		
		


//		InstagramSeleniumCrawler crawler = new InstagramSeleniumCrawler();
//		crawler.crawl();
//		
//		InstagramSeleniumCrawler ajaxCrawler = new InstagramSeleniumCrawler();
//		ajaxCrawler.ajaxCrawl();
		
//		Preprocessor preprocessor = new Preprocessor();
		
		long endTime = System.currentTimeMillis();
		long diff = (endTime - startTime) / 1000;
		logger.info("걸린 시간 : {}", diff);
		
	}
	
	public InstaStoreVO sendQueryString(StoreVO store) {
		
		Gson gson = new Gson();
		String storeName = "";
		String url = "";
		HashMap<String, ArrayList<JsonUserVO>> userMap = new HashMap<String, ArrayList<JsonUserVO>>();
		HashMap<String, ArrayList<JsonLocationVO>> locationMap = new HashMap<String, ArrayList<JsonLocationVO>>();
	
		try {
			storeName = URLEncoder.encode(store.getStore_name(), "UTF-8");
			url = "https://www.instagram.com/web/search/topsearch/?context=blended&query=%24" + storeName + "%20%40" + storeName + "%20%23" + storeName;
			JSONObject json = JsonReader.readJsonFromUrl(url);
						
			JSONArray users = json.getJSONArray("users");
			if(!users.isEmpty()) {
				for(int i = 0; i < users.length(); i++) {
					JSONObject user = users.getJSONObject(i).getJSONObject("user");
					if(user.getBoolean("is_private") == false
//							&& user.getString("full_name").trim().startsWith(store.getStore_name())) {
							&& user.getString("full_name").trim().equals(store.getStore_name())) {
						String key = Preprocessor.stringReplace(user.getString("full_name").trim());
						ArrayList<JsonUserVO> userList = new ArrayList<JsonUserVO>();
						if(userMap.containsKey(key)) {
							userList = userMap.get(key);
							userList.add(gson.fromJson(user.toString(), JsonUserVO.class));
						} else {
							userList.add(gson.fromJson(user.toString(), JsonUserVO.class));
						}
						userMap.put(key, userList);
					}
				}			
	//			for(String key : userMap.keySet()) {
	//				logger.info("{}", key);
	//			}
			} else {
//				logger.info("users is empty!!");
				return null;
			}
			
			JSONArray places = json.getJSONArray("places");
			if(!places.isEmpty()) {
				for(int i = 0; i < places.length(); i++) {
					JSONObject location = places.getJSONObject(i).getJSONObject("place").getJSONObject("location");
//					if(location.getString("name").trim().startsWith(store.getStore_name())			
					if(location.getString("name").trim().equals(store.getStore_name())
							&& (Math.abs(store.getStore_x() - location.getDouble("lng")) < 0.001)
							&& (Math.abs(store.getStore_y() - location.getDouble("lat")) < 0.001)) {
						String key = Preprocessor.stringReplace(location.getString("name").trim());
						ArrayList<JsonLocationVO> locationList = new ArrayList<JsonLocationVO>();
						if(locationMap.containsKey(key)) {
							locationList = locationMap.get(key);
							locationList.add(gson.fromJson(location.toString(), JsonLocationVO.class));
						} else {
							locationList.add(gson.fromJson(location.toString(), JsonLocationVO.class));
						}
						locationMap.put(key, locationList);
					}
				}
	//			for(String key : locationMap.keySet()) {
	//				logger.info("{}", key);
	//			}
			} else {
//				logger.info("places is empty!!");
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		InstaStoreVO iStore = new InstaStoreVO();
		ArrayList<InstaUserInfoVO> iUserList = new ArrayList<InstaUserInfoVO>();
		HashMap<String, ArrayList<InstaUserInfoVO>> iUserMap = new HashMap<String, ArrayList<InstaUserInfoVO>>();
		ArrayList<InstaLocationInfoVO> iLocationList = new ArrayList<InstaLocationInfoVO>();
		HashMap<String, ArrayList<InstaLocationInfoVO>> iLocationMap = new HashMap<String, ArrayList<InstaLocationInfoVO>>();
		
		try {
			iStore.setStore_no(store.getStore_no());
			
			for(String key : userMap.keySet()) {
				ArrayList<JsonUserVO> uList = userMap.get(key);
				
				for(JsonUserVO u : uList) {
					InstaUserInfoVO iUser = new InstaUserInfoVO();
					iUser.setInsta_id(u.getUsername());
					iUser.setProfile_pic_url(u.getProfile_pic_url());
					if(iUserMap.containsKey(key)) {
						iUserList = iUserMap.get(key);
						iUserList.add(iUser);
					} else {
						iUserList.add(iUser);
					}
				}
				iUserMap.put(key, iUserList);
			}
			iStore.setInsta_users(iUserMap);
			
			for(String key : locationMap.keySet()) {
				ArrayList<JsonLocationVO> lList = locationMap.get(key);
				
				for(JsonLocationVO l : lList) {
					InstaLocationInfoVO iLocation = new InstaLocationInfoVO();
					iLocation.setLocation_id(l.getFacebook_places_id());
					iLocation.setLocation_x(l.getLng());
					iLocation.setLocation_y(l.getLat());
					if(iLocationMap.containsKey(key)) {
						iLocationList = iLocationMap.get(key);
						iLocationList.add(iLocation);
					} else {
						iLocationList.add(iLocation);
					}
				}
				iLocationMap.put(key, iLocationList);
			}
			iStore.setInsta_locations(iLocationMap);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(iStore.getInsta_users() == null || iStore.getInsta_users().size() == 0) {
			return null;
		}
		if(iStore.getInsta_locations() == null || iStore.getInsta_locations().size() == 0) {
			return null;
		}
		return iStore;
	}
}
