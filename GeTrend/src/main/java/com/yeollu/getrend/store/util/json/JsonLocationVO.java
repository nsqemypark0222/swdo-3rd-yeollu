package com.yeollu.getrend.store.util.json;

import lombok.Data;

/**
 * @Class 	: JsonLocationVO.java
 * @Package	: com.yeollu.getrend.store.util.json
 * @Project : GeTrend
 * @Author	: 조은채, 박민열
 * @Since	: 2020. 3. 26.
 * @Version	: 1.0
 * @Desc	: 인스타그램으로부터 받은 결과값에서 JSON 형식의 location 정보에 대한 VO 역할을 수행한다. 
 */
@Data
public class JsonLocationVO {
	private String pk;
	private String name;
	private String address;
	private String city;
	private String short_name;
	private double lng;
	private double lat;
	private String external_source;
	private String facebook_places_id;
}
