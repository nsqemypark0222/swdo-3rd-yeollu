package com.yeollu.getrend.store.util.map;

/**
 * @Class 	: LocationDistance.java
 * @Package	: com.yeollu.getrend.store.util.map
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 3. 23.
 * @Version	: 1.0
 * @Desc	: 두 위경도의 거리를 구한다.
 */
public class LocationDistance {
	
	/**
	 * Fields
	 */
	public static final double R = 6372.8;
	
	/**
	 * @Method	: haversine
	 * @Return	: double
	 * @Author	: 박민열
	 * @Since	: 2020. 3. 23.
	 * @Version	: 1.0
	 * @Desc	: 두 곳의 위도와 경도를 각각 입력받아 haversine formula를 적용하여 거리를 구해 반환한다.
	 * @param lng1
	 * @param lat1
	 * @param lng2
	 * @param lat2
	 */
	public static double haversine(double lng1, double lat1, double lng2, double lat2) {
		double dLng = Math.toRadians(lng2 - lng1);
		double dLat = Math.toRadians(lat2 - lat1);
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);
		
		double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLng / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
		double c = 2 * Math.asin(Math.sqrt(a));
		return R * c;
	}
}
