package com.yeollu.getrend.store.util.map;

public class LocationDistance {
	public static final double R = 6372.8;
	
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
