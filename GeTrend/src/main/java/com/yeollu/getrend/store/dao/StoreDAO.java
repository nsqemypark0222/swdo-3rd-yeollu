package com.yeollu.getrend.store.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.store.vo.InstaStoreVO;
import com.yeollu.getrend.store.vo.StoreVO;

@Repository
public class StoreDAO {
	@Autowired
	private SqlSession session;
	
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
	
	public ArrayList<String> autoStoreAdr1_01(){
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			StoreMapper mapper = session.getMapper(StoreMapper.class);
			list = mapper.autoStoreAdr1_01();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	public ArrayList<String> autoStoreAdr1_02(){
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			StoreMapper mapper = session.getMapper(StoreMapper.class);
			list = mapper.autoStoreAdr1_02();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	public ArrayList<String> autoStoreCate3_01(){
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			StoreMapper mapper = session.getMapper(StoreMapper.class);
			list = mapper.autoStoreCate3_01();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	public ArrayList<String> autoStoreCate3_02(){
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			StoreMapper mapper = session.getMapper(StoreMapper.class);
			list = mapper.autoStoreCate3_02();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	public ArrayList<String> autoStoreCate3_03(){
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			StoreMapper mapper = session.getMapper(StoreMapper.class);
			list = mapper.autoStoreCate3_03();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
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
}
