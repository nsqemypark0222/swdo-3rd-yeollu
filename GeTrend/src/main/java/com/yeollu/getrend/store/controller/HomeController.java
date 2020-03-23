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
import com.yeollu.getrend.dataPreprocess.core.Preprocessor;
import com.yeollu.getrend.json.core.JsonReader;
import com.yeollu.getrend.json.vo.JsonLocationVO;
import com.yeollu.getrend.json.vo.JsonUserVO;
import com.yeollu.getrend.map.core.LocationDistance;
import com.yeollu.getrend.map.core.Polygon;
import com.yeollu.getrend.map.vo.Point;
import com.yeollu.getrend.store.dao.InstaLocationDAO;
import com.yeollu.getrend.store.dao.InstaUserDAO;
import com.yeollu.getrend.store.dao.SearchedDAO;
import com.yeollu.getrend.store.dao.StoreDAO;
import com.yeollu.getrend.store.vo.InstaLocationVO;
import com.yeollu.getrend.store.vo.InstaStoreVO;
import com.yeollu.getrend.store.vo.InstaUserVO;
import com.yeollu.getrend.store.vo.SearchedVO;
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
	
	@Autowired
	private SearchedDAO searchedDAO;
		
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
		
		
		Polygon polygon = new Polygon();
		for(int i = 0; i < points.size(); i++) {
			polygon.addPoint(points.get(i));
		}
		
		ArrayList<StoreVO> selectedList = new ArrayList<StoreVO>();
		
		for(int i = 0; i < list.size(); i++) {
			if(polygon.isContains(list.get(i).getStore_x(), list.get(i).getStore_y())) {
				selectedList.add(list.get(i));
			}	
		}
		logger.info("1 : {}", selectedList.size());
		
		for(Iterator<StoreVO> iterator = selectedList.iterator(); iterator.hasNext(); ) {
			if(instaUserDAO.isExistedInstaUserId(iterator.next().getStore_no())) {
				iterator.remove();
			}
		}
		logger.info("2 : {}", selectedList.size());
		
		for(Iterator<StoreVO> iterator = selectedList.iterator(); iterator.hasNext(); ) {
			if(instaLocationDAO.isExistedInstaLocationId(iterator.next().getStore_no())) {
				iterator.remove();
			}
		}
		logger.info("3 : {}", selectedList.size());
		
//		for(Iterator<StoreVO> iterator = selectedList.iterator(); iterator.hasNext(); ) {
//			StoreVO store = iterator.next();
//			if(!searchedDAO.isExistedSearchedTerm(store.getStore_name())) {
//				SearchedVO searchedTerm = new SearchedVO();
//				searchedTerm.setStore_name(store.getStore_name());
//				if(searchedDAO.insertSearchedTerm(searchedTerm) > 0) {
//					iterator.remove();
//				}
//			}
//		}
		
		logger.info("4 : {}", selectedList.size());

		for(StoreVO store : selectedList) {
			InstaStoreVO instaStore = sendQueryString(store);
			
			if(instaStore != null) {
				ArrayList<InstaUserVO> instaUserList = instaStore.getInsta_users().get(store.getStore_name());
				if(instaUserDAO.insertInstaUserList(instaUserList) > 0) {
					logger.info("insert insta user list success");
				} else {
					logger.info("insert insta user list fail");
				}
				
				ArrayList<InstaLocationVO> instaLocationList = instaStore.getInsta_locations().get(store.getStore_name());
				if(instaLocationDAO.insertInstaLocationList(instaLocationList) > 0) {
					logger.info("insert insta location list success");
				} else {
					logger.info("insert insta location list fail");
				}
				
////				for(int i = 0; i < instaUserList.size(); i++) {
////					if(instaUserDAO.insertInstaUser(instaUserList.get(i)) > 0) {
////						logger.info("insert insta user success");
////					} else {
////						logger.info("insert insta user fail");
////					}
////				}
//				
////				for(int i = 0; i < instaLocationList.size(); i++) {
////					if(instaLocationDAO.insertInstaLocation(instaLocationList.get(i)) > 0) {
////						logger.info("insert insta location success");
////					} else {
////						logger.info("insert insta location fail");
////					}
////				}
				logger.info("{}", instaStore);
			} else {
				logger.info("instaStore is null");
			}
		}
		

		
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
//				for(String key : userMap.keySet()) {
//					logger.info("{}", key);
//				}
			} else {
				logger.info("users is empty!!");
				return null;
			}
			
			JSONArray places = json.getJSONArray("places");
			if(!places.isEmpty()) {
				for(int i = 0; i < places.length(); i++) {
					JSONObject location = places.getJSONObject(i).getJSONObject("place").getJSONObject("location");
					if(location.isNull("lng") || location.isNull("lat")) {
						continue;
					}
//					if(location.getString("name").trim().startsWith(store.getStore_name())		
					if(location.getString("name").trim().equals(store.getStore_name())
							&& (LocationDistance.haversine(store.getStore_x(), store.getStore_y(), location.getDouble("lng"), location.getDouble("lat"))) < 0.5) {
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
//				for(String key : locationMap.keySet()) {
//					logger.info("{}", key);
//				}
			} else {
				logger.info("places is empty!!");
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		InstaStoreVO instaStore = new InstaStoreVO();
		ArrayList<InstaUserVO> instaUserList = new ArrayList<InstaUserVO>();
		HashMap<String, ArrayList<InstaUserVO>> instaUserMap = new HashMap<String, ArrayList<InstaUserVO>>();
		ArrayList<InstaLocationVO> instaLocationList = new ArrayList<InstaLocationVO>();
		HashMap<String, ArrayList<InstaLocationVO>> instaLocationMap = new HashMap<String, ArrayList<InstaLocationVO>>();
		
		try {
			instaStore.setStore_no(store.getStore_no());
			
			for(String key : userMap.keySet()) {
				ArrayList<JsonUserVO> uList = userMap.get(key);
				
				for(JsonUserVO u : uList) {
					InstaUserVO iUser = new InstaUserVO();
					iUser.setInsta_id(u.getUsername());
					iUser.setStore_no(store.getStore_no());
					iUser.setProfile_pic_url(u.getProfile_pic_url());
					if(instaUserMap.containsKey(key)) {
						instaUserList = instaUserMap.get(key);
						instaUserList.add(iUser);
					} else {
						instaUserList.add(iUser);
					}
				}
				instaUserMap.put(key, instaUserList);
			}
			instaStore.setInsta_users(instaUserMap);
			
			for(String key : locationMap.keySet()) {
				ArrayList<JsonLocationVO> lList = locationMap.get(key);
				
				for(JsonLocationVO l : lList) {
					InstaLocationVO iLocation = new InstaLocationVO();
					iLocation.setLocation_id(l.getFacebook_places_id());
					iLocation.setStore_no(store.getStore_no());
					iLocation.setLocation_x(l.getLng());
					iLocation.setLocation_y(l.getLat());
					if(instaLocationMap.containsKey(key)) {
						instaLocationList = instaLocationMap.get(key);
						instaLocationList.add(iLocation);
					} else {
						instaLocationList.add(iLocation);
					}
				}
				instaLocationMap.put(key, instaLocationList);
			}
			instaStore.setInsta_locations(instaLocationMap);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(instaStore.getInsta_users() == null || instaStore.getInsta_users().size() == 0) {
			return null;
		}
		if(instaStore.getInsta_locations() == null || instaStore.getInsta_locations().size() == 0) {
			return null;
		}
		return instaStore;
	}
}
