package com.yeollu.getrend.store.util.preprocess.core;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yeollu.getrend.store.util.json.core.JsonReader;
import com.yeollu.getrend.store.util.map.core.LocationDistance;
import com.yeollu.getrend.store.util.preprocess.model.DistVO;
import com.yeollu.getrend.store.vo.StoreVO;

public class QueryStringSender {
	
	private static final Logger logger = LoggerFactory.getLogger(QueryStringSender.class);
	
	public static String send(StoreVO store) {
		String storeName = "";
		String url = "";
		String location_id= "";
		
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
	
//				HashMap<String, DistVO> distMap = new HashMap<String, DistVO>();
				for(int i = 0; i < places.length(); i++) {
					JSONObject location = places
											.getJSONObject(i)
											.getJSONObject("place")
											.getJSONObject("location");
					if(location.isNull("lng") || location.isNull("lat")) {
						continue;
					}
					if(location.getString("name").trim().contains(store.getStore_name())) {
						double d = LocationDistance
												.haversine(store.getStore_x(), store.getStore_y(), location.getDouble("lng"), location.getDouble("lat"));
						if(d < 0.1) {
							return location.getString("pk");
//							DistVO dist = new DistVO();
//							dist.setLocation_id(location.get("facebook_places_id") + "");
//							dist.setDist(d);
//							distMap.put(location.getString("pk"), dist);
						}
					}
				}
				
//				if(!distMap.isEmpty()) {
//					키값이 일정하지 않아서 반복문을 쓸 수 없는 상태임
//				}
//				location_id = places
//								.getJSONObject(?????)
//								.getJSONObject("place")
//								.getJSONObject("location")
//								.getString("pk");
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return location_id;
	}
}

