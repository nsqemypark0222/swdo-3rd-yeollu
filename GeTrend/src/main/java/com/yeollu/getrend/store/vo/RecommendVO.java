package com.yeollu.getrend.store.vo;

import lombok.Data;

/**
 * @Class 	: RecommendVO.java
 * @Package	: com.yeollu.getrend.store.vo
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 4. 19.
 * @Version	: 1.0
 * @Desc	: recommend 테이블의 VO 역할을 수행한다.
 */
@Data
public class RecommendVO {
	private String store_adr;
	private String store_cate1;
	private String recommend_indate;
}
