package com.yeollu.getrend.mango.vo;

import lombok.Data;

/**
 * @Class 	: MangoStoreVO.java
 * @Package	: com.yeollu.getrend.mango.vo
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 4. 19.
 * @Version	: 1.0
 * @Desc	: mango_stores 테이블의 VO 역할을 수행한다.
 */
@Data
public class MangoStoreVO {
	private String store_no;
	private String mango_tel;
	private String mango_kind;
	private String mango_price;
	private String mango_parking;
	private String mango_time;
	private String mango_break_time;
	private String mango_last_order;
	private String mango_holiday;
	private String mango_indate;
}
