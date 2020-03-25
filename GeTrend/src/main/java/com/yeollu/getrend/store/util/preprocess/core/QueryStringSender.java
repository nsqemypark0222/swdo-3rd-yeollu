package com.yeollu.getrend.store.util.preprocess.core;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.yeollu.getrend.store.util.json.core.JsonReader;
import com.yeollu.getrend.store.util.json.vo.JsonLocationVO;
import com.yeollu.getrend.store.util.map.core.LocationDistance;
import com.yeollu.getrend.store.vo.InstaLocationVO;
import com.yeollu.getrend.store.vo.InstaStoreVO;
import com.yeollu.getrend.store.vo.StoreVO;

public class QueryStringSender {
	
	private static final Logger logger = LoggerFactory.getLogger(QueryStringSender.class);

	public static InstaStoreVO send(StoreVO store) {
		Gson gson = new Gson();
		String storeName = "";
		String url = "";
		HashMap<String, ArrayList<JsonLocationVO>> locationMap = new HashMap<String, ArrayList<JsonLocationVO>>();
	
		try {			
			storeName = URLEncoder.encode(store.getStore_name(), "UTF-8");
			url = "https://www.instagram.com/web/search/topsearch/?context=blended&query=%24" + storeName + "%20%40" + storeName + "%20%23" + storeName;
			JSONObject json = JsonReader.readJsonFromUrl(url);
						
			if(json == null) {
				logger.info("json is null");
				return null;
			}
			
			JSONArray places = json.getJSONArray("places");
			if(!places.isEmpty()) {
				for(int i = 0; i < places.length(); i++) {
					JSONObject location = places.getJSONObject(i).getJSONObject("place").getJSONObject("location");
					if(location.isNull("lng") || location.isNull("lat")) {
						continue;
					}
					if(location.getString("name").trim().contains(store.getStore_name())
							&& (LocationDistance.haversine(store.getStore_x(), store.getStore_y(), location.getDouble("lng"), location.getDouble("lat"))) < 0.1) {
						String key = store.getStore_no();
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
		ArrayList<InstaLocationVO> instaLocationList = new ArrayList<InstaLocationVO>();
		HashMap<String, ArrayList<InstaLocationVO>> instaLocationMap = new HashMap<String, ArrayList<InstaLocationVO>>();
		
		try {
			instaStore.setStore_no(store.getStore_no());
			
			for(String key : locationMap.keySet()) {
				ArrayList<JsonLocationVO> lList = locationMap.get(key);
				
				for(JsonLocationVO l : lList) {
					InstaLocationVO iLocation = new InstaLocationVO();
					iLocation.setLocation_pk(l.getPk());
					iLocation.setStore_no(store.getStore_no());
					iLocation.setLocation_x(l.getLng());
					iLocation.setLocation_y(l.getLat());
					iLocation.setLocation_id(l.getFacebook_places_id());
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
		
		if(instaStore.getInsta_locations() == null || instaStore.getInsta_locations().size() == 0) {
			return null;
		}
		return instaStore;
	}
}
