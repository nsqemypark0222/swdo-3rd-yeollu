package com.yeollu.getrend.store.vo;

import java.util.ArrayList;
import java.util.HashMap;

import lombok.Data;

@Data
public class InstaStoreVO {
	private String store_no;
	private HashMap<String, ArrayList<InstaUserInfoVO>> insta_users;
	private HashMap<String, ArrayList<InstaLocationInfoVO>> insta_locations;
}
