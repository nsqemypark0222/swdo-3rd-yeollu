package com.yeollu.getrend.store.vo;

import java.util.ArrayList;

import lombok.Data;

@Data
public class InstaStoreInfoVO {
	private InstaStoreVO instaStore;
	private ArrayList<InstaImageVO> instaImageList;
	private MangoStoreInfoVO mangoStoreInfo;
	private ScoreVO score;
}
