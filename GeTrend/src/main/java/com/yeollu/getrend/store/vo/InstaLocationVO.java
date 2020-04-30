package com.yeollu.getrend.store.vo;

import lombok.Data;

/**
 * @Class 	: InstaLocationVO.java
 * @Package	: com.yeollu.getrend.store.vo
 * @Project : GeTrend
 * @Author	: 조은채, 박민열
 * @Since	: 2020. 3. 26.
 * @Version	: 1.0
 * @Desc	: insta_locations 테이블의 VO 역할을 수행한다.
 */
@Data
public class InstaLocationVO {
	private String location_id;
	private String store_no;
	private String indate;
}
