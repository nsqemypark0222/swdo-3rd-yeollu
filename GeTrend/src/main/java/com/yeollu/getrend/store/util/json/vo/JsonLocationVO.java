package com.yeollu.getrend.json.vo;

import lombok.Data;

@Data
public class JsonLocationVO {
	private String pk;
	private String name;
	private String address;
	private String city;
	private String short_name;
	private double lng;
	private double lat;
	private String external_source;
	private String facebook_places_id;
}
