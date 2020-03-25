package com.yeollu.getrend.store.vo;

import lombok.Data;

@Data
public class InstaLocationVO {
	private int location_no;
	private String location_pk;
	private String location_id;
	private String store_no;
	private double location_x;
	private double location_y;
	private String indate;
}
