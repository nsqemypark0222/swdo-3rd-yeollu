package com.yeollu.getrend.store.vo;

import java.util.ArrayList;

import com.yeollu.getrend.store.util.map.Point;

import lombok.Data;

/**
 * @Class 	: ReqParmVO.java
 * @Package	: com.yeollu.getrend.store.vo
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 4. 01.
 * @Version	: 1.0
 * @Desc	: ReqParmVO 역할을 수행한다.
 */
@Data
public class ReqParmVO {
	private ArrayList<Point> points;
	private ArrayList<String> categoryValues;
	private ArrayList<String> opentimeValues;
}
