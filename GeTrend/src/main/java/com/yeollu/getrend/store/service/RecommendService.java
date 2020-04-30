package com.yeollu.getrend.store.service;

import java.util.ArrayList;

import com.yeollu.getrend.store.vo.InstaStoreInfoVO;
import com.yeollu.getrend.store.vo.RecommendVO;

/**
 * @Class 	: RecommendService.java
 * @Package	: com.yeollu.getrend.store.service
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 4. 19.
 * @Version	: 1.0
 * @Desc	: 가게 추천 관련 작업을 수행하기 위한 Service interface 역할을 수행한다.
 */
public interface RecommendService {
	public ArrayList<RecommendVO> selectAllRecommendList();
	public void updateRecommendStores(RecommendVO recommend);
	public ArrayList<InstaStoreInfoVO> generateInstaStoreInfo(RecommendVO recommendList);

}
