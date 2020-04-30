package com.yeollu.getrend.store.vo;

import lombok.Data;

/**
 * @Class 	: ScoreVO.java
 * @Package	: com.yeollu.getrend.store.vo
 * @Project : GeTrend
 * @Author	: 조은채, 박민열
 * @Since	: 2020. 4. 11.
 * @Version	: 1.0
 * @Desc	: scores 테이블의 VO 역할을 수행한다.
 */
@Data
public class ScoreVO {
	private String store_no;
	private int sum_of_like;
	private double avg_of_star;
	private int sum_of_insta_like;
}
