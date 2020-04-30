package com.yeollu.getrend.store.vo;

import lombok.Data;

/**
 * @Class 	: MangoStoreInfoVO.java
 * @Package	: com.yeollu.getrend.store.vo
 * @Project : GeTrend
 * @Author	: 조은채, 박민열
 * @Since	: 2020. 3. 26.
 * @Version	: 1.0
 * @Desc	:
 */
@Data
public class MangoStoreInfoVO {
	private String store_no;
	private String mango_tel;
	private String mango_price;
	private String mango_parking;
	private String mango_start;
	private String mango_end;
	private String mango_sun;
	private String mango_mon;
	private String mango_tue;
	private String mango_wed;
	private String mango_thu;
	private String mango_fri;
	private String mango_sat;
}
