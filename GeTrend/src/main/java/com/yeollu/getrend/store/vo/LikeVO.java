package com.yeollu.getrend.store.vo;

import lombok.Data;

/**
 * @Class 	: LikeVO.java
 * @Package	: com.yeollu.getrend.store.vo
 * @Project : GeTrend
 * @Author	: 조은채
 * @Since	: 2020. 4. 11.
 * @Version	: 1.0
 * @Desc	: likes 테이블의 VO 역할을 수행한다.
 */
@Data
public class LikeVO {
	private String store_no;
	private String user_email;
	private String likes_indate;
}