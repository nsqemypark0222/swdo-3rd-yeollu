package com.yeollu.getrend.store.vo;

import java.util.ArrayList;

import lombok.Data;

/**
 * @Class 	: InstaStoreInfoVO.java
 * @Package	: com.yeollu.getrend.store.vo
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 3. 26.
 * @Version	: 1.0
 * @Desc	: InstaStoreInfoVO 역할을 수행한다.
 */
@Data
public class InstaStoreInfoVO {
	private InstaStoreVO instaStore;
	private ArrayList<InstaImageVO> instaImageList;
	private MangoStoreInfoVO mangoStoreInfo;
	private ScoreVO score;
}
