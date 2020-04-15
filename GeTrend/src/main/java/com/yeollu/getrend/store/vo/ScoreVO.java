package com.yeollu.getrend.store.vo;

import lombok.Data;

@Data
public class ScoreVO {
	private String store_no;
	private int sum_of_like;
	private double sum_of_star;
	private int sum_of_insta_like;
}
