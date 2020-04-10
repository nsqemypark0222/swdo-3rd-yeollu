package com.yeollu.getrend.store.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.store.vo.MangoDayVO;
import com.yeollu.getrend.store.vo.MangoStoreInfoVO;
import com.yeollu.getrend.store.vo.MangoStoreVO;

@Repository
public class MangoStoreDAO {
	
	@Autowired
	private SqlSession session;
	
	public int insertMangoStore(MangoStoreVO mangoStore) {
		int cnt = 0;
		
		try {
			MangoStoreMapper mapper = session.getMapper(MangoStoreMapper.class);
			cnt = mapper.insertMangoStore(mangoStore);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public ArrayList<MangoStoreVO> selectAllMangoStores() {
		ArrayList<MangoStoreVO> list = new ArrayList<MangoStoreVO>();
		try {
			MangoStoreMapper mapper = session.getMapper(MangoStoreMapper.class);
			list = mapper.selectAllMangoStores();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public MangoStoreVO selectMangoStoreByStoreNo(String store_no) {
		MangoStoreVO mangoStore = new MangoStoreVO();
		
		try {
			MangoStoreMapper mapper = session.getMapper(MangoStoreMapper.class);
			mangoStore = mapper.selectMangoStoreByStoreNo(store_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mangoStore;
	}
	
	public ArrayList<MangoStoreVO> selectMangoStoreByMangoDay(MangoDayVO mangoDay) {
		ArrayList<MangoStoreVO> list = new ArrayList<MangoStoreVO>();
		try {
			MangoStoreMapper mapper = session.getMapper(MangoStoreMapper.class);
			list = mapper.selectMangoStoreByMangoDay(mangoDay);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public MangoStoreInfoVO selectMangoStoreInfoByStoreNo(String store_no) {
		MangoStoreInfoVO mangoStoreInfo = new MangoStoreInfoVO();
		try {
			MangoStoreMapper mapper = session.getMapper(MangoStoreMapper.class);
			mangoStoreInfo = mapper.selectMangoStoreInfoByStoreNo(store_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mangoStoreInfo;
	}
	
	public MangoStoreInfoVO selectMangoStoreInfoByDaysAndTimes(String store_no, ArrayList<String> opentimeValues) {
		MangoStoreInfoVO mangoStoreInfo = new MangoStoreInfoVO();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("store_no", store_no);
		map.put("mango_sun", "0");
		map.put("mango_mon", "0");
		map.put("mango_tue", "0");
		map.put("mango_wed", "0");
		map.put("mango_thu", "0");
		map.put("mango_fri", "0");
		map.put("mango_sat", "0");
		
		for(String str : opentimeValues) {
			if(str.equals("일")) {
				map.put("mango_sun", "1");
			} else if(str.equals("월")) {
				map.put("mango_mon", "1");
			} else if(str.equals("화")) {
				map.put("mango_tue", "1");
			} else if(str.equals("수")) {
				map.put("mango_wed", "1");
			} else if(str.equals("목")) {
				map.put("mango_thu", "1");
			} else if(str.equals("금")) {
				map.put("mango_fri", "1");
			} else if(str.equals("토")) {
				map.put("mango_sat", "1");
			}
		}
		
		try {
			MangoStoreMapper mapper = session.getMapper(MangoStoreMapper.class);
			mangoStoreInfo = mapper.selectMangoStoreInfoByDaysAndTimes(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mangoStoreInfo;
	}

}
