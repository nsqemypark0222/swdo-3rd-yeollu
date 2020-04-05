package com.yeollu.getrend.store.vo;

import java.util.ArrayList;

import com.yeollu.getrend.store.util.map.model.Point;

import lombok.Data;

@Data
public class ReqParmVO {
	private ArrayList<Point> points;
	private ArrayList<String> categoryValues;
	private ArrayList<String> opentimeValues;
}
