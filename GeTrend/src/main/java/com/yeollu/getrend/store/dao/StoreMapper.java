package com.yeollu.getrend.store.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.yeollu.getrend.store.vo.InstaStoreVO;
import com.yeollu.getrend.store.vo.StoreVO;

/**
 * @Class 	: StoreMapper.java
 * @Package	: com.yeollu.getrend.store.dao
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 3. 12.
 * @Version	: 1.0
 * @Desc	: stores 테이블의 Mapper interface 역할을 수행한다
 */
public interface StoreMapper {
	public ArrayList<StoreVO> selectAllStores();
	public ArrayList<StoreVO> selectStoresWithMangoStores();
	public InstaStoreVO selectInstaStore(String store_no);
	public ArrayList<StoreVO> searchStoresByTerm(String term);
	public ArrayList<StoreVO> selectStoresByStoreAdr(String store_adr);
	public ArrayList<StoreVO> selectStoresByStoreCate1(ArrayList<String> categoryValues);
	public ArrayList<StoreVO> selectStoresByStoreAdrAndStoreCate1(HashMap<String, String> hashmap);
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
