package com.yeollu.getrend.store.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.yeollu.getrend.store.vo.InstaStoreInfoVO;
import com.yeollu.getrend.store.vo.RecommendVO;

public interface RecommendService {
	
	public ArrayList<RecommendVO> selectAllRecommendList();
	
	public void updateRecommendStores(RecommendVO recommend);
	
	public ArrayList<InstaStoreInfoVO> generateInstaStoreInfo(RecommendVO recommendList);

}
