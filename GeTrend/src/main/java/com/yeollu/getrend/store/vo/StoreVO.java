package com.yeollu.getrend.store.vo;

import lombok.Data;

/**
 * @Class 	: StoreVO.java
 * @Package	: com.yeollu.getrend.store.vo
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 3. 12.
 * @Version	: 1.0
 * @Desc	: stores 테이블의 VO 역할을 수행한다.
 */
@Data
public class StoreVO {
	private String store_no;
	private String store_name;
	private String store_name2;
	private String store_cate1;
	private String store_cate2;
	private String store_adr;
	private String store_adr1;
	private String store_adr2;
	private double store_x;
	private double store_y;
}