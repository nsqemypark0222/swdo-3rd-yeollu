package com.yeollu.getrend.mango.vo;

import lombok.Data;

/**
 * @Class 	: MangoDayVO.java
 * @Package	: com.yeollu.getrend.mango.vo
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 4. 19.
 * @Version	: 1.0
 * @Desc	: mango_days 테이블의 VO 역할을 수행한다.
 */
@Data
public class MangoDayVO {
	private String store_no;
	private String mango_sun;
	private String mango_mon;
	private String mango_tue;
	private String mango_wed;
	private String mango_thu;
	private String mango_fri;
	private String mango_sat;
	private String mango_indate;
}
