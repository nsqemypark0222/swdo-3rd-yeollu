package com.yeollu.getrend.store.vo;

import lombok.Data;

/**
 * @Class 	: InstaImageVO.java
 * @Package	: com.yeollu.getrend.store.vo
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 3. 26.
 * @Version	: 1.0
 * @Desc	: insta_images 테이블의 테이블의 VO 역할을 수행한다.
 */
@Data
public class InstaImageVO {
	private int image_no;
	private String store_no;
	private String image_type;
	private String image_url;
	private int image_like;
	private String image_indate;
}
