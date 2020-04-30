package com.yeollu.getrend.store.service;

import java.util.ArrayList;

import com.yeollu.getrend.store.vo.InstaImageVO;
import com.yeollu.getrend.store.vo.InstaStoreInfoVO;
import com.yeollu.getrend.store.vo.InstaStoreVO;
import com.yeollu.getrend.store.vo.MangoStoreInfoVO;
import com.yeollu.getrend.store.vo.ScoreVO;
import com.yeollu.getrend.store.vo.StoreVO;

/**
 * @Class 	: StoreService.java
 * @Package	: com.yeollu.getrend.store.service
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 3. 23.
 * @Version	: 1.0
 * @Desc	: 요청 받은 가게 정보 객체 리스트를 생성하기 위한 Service interface 역할을 수행한다.
 */
public interface StoreService {
	public ArrayList<InstaStoreVO> generateInstaStoreList(ArrayList<StoreVO> storeList);
	public ArrayList<MangoStoreInfoVO> generateMangoStoreInfoList(ArrayList<InstaStoreVO> instaStoreList, ArrayList<String> opentimeValues);
	public ArrayList<ScoreVO> generateScoreList(ArrayList<InstaStoreVO> instaStoreList);
	public ArrayList<InstaStoreVO> sortInstaStoreList(ArrayList<InstaStoreVO> instaStoreList, ArrayList<ScoreVO> scoreList);
	public ArrayList<InstaImageVO> requestCrawling(ArrayList<InstaStoreVO> instaStoreList);
	public ArrayList<InstaStoreInfoVO> generateInstaStoreInfoList(ArrayList<InstaStoreVO> instaStoreList
																	, ArrayList<MangoStoreInfoVO> mangoStoreInfoList
																	, ArrayList<ScoreVO> scoreList
																	, ArrayList<InstaImageVO> instaImageList);
	public ArrayList<InstaStoreInfoVO> sortInstaStoreInfoList(ArrayList<InstaStoreInfoVO> instaStoreInfoList);
	public InstaStoreInfoVO generateInstaStoreInfo(String store_no);
	public InstaStoreInfoVO updateInstaStoreInfo(String store_no);
}
