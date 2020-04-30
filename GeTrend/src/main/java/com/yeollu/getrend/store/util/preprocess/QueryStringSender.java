package com.yeollu.getrend.store.util.preprocess;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yeollu.getrend.store.util.json.JsonReader;
import com.yeollu.getrend.store.util.map.LocationDistance;
import com.yeollu.getrend.store.vo.StoreVO;

/**
 * @Class 	: QueryStringSender.java
 * @Package	: com.yeollu.getrend.store.util.preprocess
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 3. 23.
 * @Version	: 1.0
 * @Desc	: 인스타그램에 쿼리스트링을 보내 필요한 정보를 찾아낸다.
 */
public class QueryStringSender {
	
	/**
	 * Fields
	 */
	private static final Logger logger = LoggerFactory.getLogger(QueryStringSender.class);
	
	/**
	 * @Method	: send
	 * @Return	: String
	 * @Author	: 박민열
	 * @Since	: 2020. 3. 23.
	 * @Version	: 1.0
	 * @Desc	: 인스타그램에 쿼리스트링을 보내 결과값에서 필요한 location_id를 찾아 반환한다.
	 * @param store
	 */
	public static String send(StoreVO store) {
		String storeName = "";
		String url = "";
		
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
	
				HashMap<String, Double> distMap = new HashMap<String, Double>();
				for(int i = 0; i < places.length(); i++) {
					JSONObject location = places
											.getJSONObject(i)
											.getJSONObject("place")
											.getJSONObject("location");
					if(location.isNull("lng") || location.isNull("lat")) {
						continue;
					}
					if(location.getString("name").trim().contains(store.getStore_name())) {
						double dist = LocationDistance
												.haversine(store.getStore_x(), store.getStore_y(), location.getDouble("lng"), location.getDouble("lat"));
						if(dist < 0.1) {
							distMap.put(location.getString("pk"), new Double(dist));
						}
					}
				}
				
				if(!distMap.isEmpty()) {
					Iterator<String> iterator = sortMap(distMap).iterator();
					if(iterator.hasNext()) {
						return iterator.next();
					}
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @Method	: sortMap
	 * @Return	: List<String>
	 * @Author	: 박민열
	 * @Since	: 2020. 3. 23.
	 * @Version	: 1.0
	 * @Desc	: 맵의 요소를 값에 따라 정렬한다.
	 * @param map
	 */
	public static List<String> sortMap(final Map<String, Double> map) {
		List<String> list = new ArrayList<String>();
		list.addAll(map.keySet());
		
		Collections.sort(list, new Comparator<Object>() {
			public int compare(Object o1, Object o2) {
				Object v1 = map.get(o1);
				Object v2 = map.get(o2);
				
				return ((Comparable<Object>) v2).compareTo(v1);
			}
		});
//		Collections.reverse(list);
		return list;
	}
}

