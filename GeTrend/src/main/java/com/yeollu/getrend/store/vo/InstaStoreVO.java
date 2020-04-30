package com.yeollu.getrend.store.vo;

import lombok.Data;

/**
 * @Class 	: InstaStoreVO.java
 * @Package	: com.yeollu.getrend.store.vo
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 3. 26.
 * @Version	: 1.0
 * @Desc	: InstaStoreVO 역할을 수행한다
 */
@Data
public class InstaStoreVO {
	private String store_no;
	private String location_id;
	private String store_x;
	private String store_y;
	private String store_name;
	private String store_name2;
	private String store_cate1;
	private String store_cate2;
	private String store_adr;
	private String store_adr1;
	private String store_adr2;
}
