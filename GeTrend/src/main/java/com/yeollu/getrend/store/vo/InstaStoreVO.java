package com.yeollu.getrend.store.vo;

import java.util.ArrayList;
import java.util.HashMap;

import lombok.Data;

@Data
public class InstaStoreVO {
	private String store_no;
//	private HashMap<String, ArrayList<InstaUserVO>> insta_users;
	private HashMap<String, ArrayList<InstaLocationVO>> insta_locations;
}
