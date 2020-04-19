package com.yeollu.getrend.store.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.store.vo.MangoStoreInfoVO;

@Repository
public class MangoStoreInfoDAO {
	
	@Autowired
	private SqlSession session;

	public MangoStoreInfoVO selectMangoStoreInfoByStoreNo(String store_no) {
		MangoStoreInfoVO mangoStoreInfo = new MangoStoreInfoVO();
		try {
			MangoStoreInfoMapper mapper = session.getMapper(MangoStoreInfoMapper.class);
			mangoStoreInfo = mapper.selectMangoStoreInfoByStoreNo(store_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mangoStoreInfo;
	}
	
	public MangoStoreInfoVO selectMangoStoreInfoByStoreNoAndDays(String store_no, ArrayList<String> opentimeValues) {
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
			MangoStoreInfoMapper mapper = session.getMapper(MangoStoreInfoMapper.class);
			mangoStoreInfo = mapper.selectMangoStoreInfoByStoreNoAndDays(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mangoStoreInfo;
	}
}
