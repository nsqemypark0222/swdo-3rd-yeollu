package com.yeollu.getrend.store.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.store.vo.InstaStoreVO;
import com.yeollu.getrend.store.vo.StoreVO;

/**
 * @Class 	: StoreDAO.java
 * @Package	: com.yeollu.getrend.store.dao
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 3. 12.
 * @Version	: 1.0
 * @Desc	: stores 테이블에 접근해 관련 작업을 수행한다.
 */
@Repository
public class StoreDAO {
	
	/**
	 * Fields
	 */
	@Autowired
	private SqlSession session;
	
	/**
	 * @Method	: selectAllStores
	 * @Return	: ArrayList<StoreVO>
	 * @Author	: 박민열
	 * @Since	: 2020. 3. 12.
	 * @Version	: 1.0
	 * @Desc	: 모든 가게를 조회한다.
	 */
	public ArrayList<StoreVO> selectAllStores() {
		ArrayList<StoreVO> list = new ArrayList<StoreVO>();
		
		try {
			StoreMapper mapper = session.getMapper(StoreMapper.class);
			list = mapper.selectAllStores();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * @Method	: selectStoresWithMangoStores
	 * @Return	: ArrayList<StoreVO>
	 * @Author	: 박민열, 조은채
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: 망고플레이트 정보가 있는 가게들을 조회한다.
	 */
	public ArrayList<StoreVO> selectStoresWithMangoStores() {
		ArrayList<StoreVO> list = new ArrayList<StoreVO>();
		
		try {
			StoreMapper mapper = session.getMapper(StoreMapper.class);
			list = mapper.selectStoresWithMangoStores();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * @Method	: selectInstaStore
	 * @Return	: InstaStoreVO
	 * @Author	: 박민열
	 * @Since	: 2020. 3. 26.
	 * @Version	: 1.0
	 * @Desc	: 인스타그램 위치 정보가 있는 가게를 조회한다.
	 * @param store_no
	 */
	public InstaStoreVO selectInstaStore(String store_no) {
		InstaStoreVO instaStore = null;
		
		try {
			StoreMapper mapper = session.getMapper(StoreMapper.class);
			instaStore = mapper.selectInstaStore(store_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return instaStore;
	}
	
	/**
	 * @Method	: searchStoresByTerm
	 * @Return	: ArrayList<StoreVO>
	 * @Author	: 오선미
	 * @Since	: 2020. 4. 11.
	 * @Version	: 1.0
	 * @Desc	: 검색 키워드로 가게들을 조회한다.
	 * @param term
	 */
	public ArrayList<StoreVO> searchStoresByTerm(String term) {
		ArrayList<StoreVO> list = new ArrayList<StoreVO>();
		
		try {
			StoreMapper mapper = session.getMapper(StoreMapper.class);
			list = mapper.searchStoresByTerm(term);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * @Method	: autoStoreName
	 * @Return	: ArrayList<String>
	 * @Author	: 조은채
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: 중복되지 않는 store_name을 조회한다.
	 */
	public ArrayList<String> autoStoreName(){
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			StoreMapper mapper = session.getMapper(StoreMapper.class);
			list = mapper.autoStoreName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * @Method	: autoStoreAdr
	 * @Return	: ArrayList<String>
	 * @Author	: 조은채
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: 중복되지 않는 store_adr를 조회한다.
	 */
	public ArrayList<String> autoStoreAdr(){
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			StoreMapper mapper = session.getMapper(StoreMapper.class);
			list = mapper.autoStoreAdr();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	/**
	 * @Method	: autoStoreCate2_01
	 * @Return	: ArrayList<String>
	 * @Author	: 조은채
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: 중복되지 않는 store_cate2를 조회한다. 01
	 */
	public ArrayList<String> autoStoreCate2_01(){
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			StoreMapper mapper = session.getMapper(StoreMapper.class);
			list = mapper.autoStoreCate2_01();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * @Method	: autoStoreCate2_02
	 * @Return	: ArrayList<String>
	 * @Author	: 조은채
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: 중복되지 않는 store_cate2를 조회한다. 02
	 */
	public ArrayList<String> autoStoreCate2_02(){
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			StoreMapper mapper = session.getMapper(StoreMapper.class);
			list = mapper.autoStoreCate2_02();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * @Method	: autoStoreCate2_03
	 * @Return	: ArrayList<String>
	 * @Author	: 조은채
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: 중복되지 않는 store_cate2를 조회한다. 03
	 */
	public ArrayList<String> autoStoreCate2_03(){
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			StoreMapper mapper = session.getMapper(StoreMapper.class);
			list = mapper.autoStoreCate2_03();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * @Method	: selectStoresByStoreAdr
	 * @Return	: ArrayList<StoreVO>
	 * @Author	: 박민열
	 * @Since	: 2020. 3. 12.
	 * @Version	: 1.0
	 * @Desc	: store_adr로 가게들을 조회한다.
	 * @param store_adr
	 */
	public ArrayList<StoreVO> selectStoresByStoreAdr(String store_adr) {
		ArrayList<StoreVO> list = new ArrayList<StoreVO>();
		
		try {
			StoreMapper mapper = session.getMapper(StoreMapper.class);
			list = mapper.selectStoresByStoreAdr(store_adr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * @Method	: selectStoresByStoreCate1
	 * @Return	: ArrayList<StoreVO>
	 * @Author	: 박민열
	 * @Since	: 2020. 3. 12.
	 * @Version	: 1.0
	 * @Desc	: 카테고리 리스트로 가게들을 조회한다.
	 * @param categoryValues
	 */
	public ArrayList<StoreVO> selectStoresByStoreCate1(ArrayList<String> categoryValues) {
		ArrayList<StoreVO> list = new ArrayList<StoreVO>();
		
		try {
			StoreMapper mapper = session.getMapper(StoreMapper.class);
			list = mapper.selectStoresByStoreCate1(categoryValues);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * @Method	: selectStoresByStoreAdrAndStoreCate1
	 * @Return	: ArrayList<StoreVO>
	  * @Author	: 박민열
	 * @Since	: 2020. 3. 12.
	 * @Version	: 1.0
	 * @Desc	: 카테고리 하나로 가게들을 조회한다.
	 * @param adr
	 * @param category
	 */
	public ArrayList<StoreVO> selectStoresByStoreAdrAndStoreCate1(String adr, String category) {
		ArrayList<StoreVO> list = new ArrayList<StoreVO>();
		HashMap<String, String> hashmap = new HashMap<String, String>();
		hashmap.put("store_adr", adr);
		hashmap.put("store_cate1", category);
		
		try {
			StoreMapper mapper = session.getMapper(StoreMapper.class);
			list = mapper.selectStoresByStoreAdrAndStoreCate1(hashmap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
