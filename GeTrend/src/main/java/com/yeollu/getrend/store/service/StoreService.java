package com.yeollu.getrend.store.service;

import java.util.ArrayList;

import com.yeollu.getrend.store.vo.InstaImageVO;
import com.yeollu.getrend.store.vo.InstaStoreInfoVO;
import com.yeollu.getrend.store.vo.InstaStoreVO;
import com.yeollu.getrend.store.vo.MangoStoreInfoVO;
import com.yeollu.getrend.store.vo.ScoreVO;
import com.yeollu.getrend.store.vo.StoreVO;

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
	
	public InstaStoreInfoVO generateIStoreInfo(String store_no);
	
	public InstaStoreInfoVO updateIStoreInfo(String store_no);
}
