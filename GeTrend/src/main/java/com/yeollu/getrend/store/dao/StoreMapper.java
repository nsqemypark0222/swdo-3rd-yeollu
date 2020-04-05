package com.yeollu.getrend.store.dao;

import java.util.ArrayList;

import com.yeollu.getrend.store.vo.InstaStoreVO;
import com.yeollu.getrend.store.vo.StoreVO;

public interface StoreMapper {
	public ArrayList<StoreVO> selectAllStores();
	public ArrayList<StoreVO> selectStoresWithMangoStores();
	public InstaStoreVO selectInstaStore(String store_no);
	public ArrayList<StoreVO> searchStoresByTerm(String term);
	public ArrayList<StoreVO> selectStoresByStoreAdr(String store_adr);
	public ArrayList<StoreVO> selectStoresByStoreCate1(ArrayList<String> categoryValues);
	public ArrayList<String> autoStoreName();
	public ArrayList<String> autoStoreAdr1_01();
	public ArrayList<String> autoStoreAdr1_02();
	public ArrayList<String> autoStoreCate3_01();
	public ArrayList<String> autoStoreCate3_02();
	public ArrayList<String> autoStoreCate3_03();
	public ArrayList<String> autoStoreAdr();
	public ArrayList<String> autoStoreCate2_01();
	public ArrayList<String> autoStoreCate2_02();
	public ArrayList<String> autoStoreCate2_03();
}
