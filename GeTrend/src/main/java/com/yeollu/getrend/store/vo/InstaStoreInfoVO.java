package com.yeollu.getrend.store.vo;

import java.util.ArrayList;

import lombok.Data;

@Data
public class InstaStoreInfoVO {
	private String store_no;
	private String location_id;
	private String store_x;
	private String store_y;
	private String store_name;
	private String store_name2;
	private String store_cate1;
	private String store_cate2;
	private String store_cate3;
	private String store_dem;
	private String store_adr1;
	private String store_adr2;
	private String profile_url;
	private ArrayList<String> imgList;
}
