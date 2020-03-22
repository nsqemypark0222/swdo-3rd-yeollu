package com.yeollu.getrend.json.core;

import lombok.Data;

@Data
public class JsonLocationVO {
	private double lng;
	private double lat;
	private String name;
	private String facebook_places_id;
}
