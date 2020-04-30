package com.yeollu.getrend.store.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.store.vo.MangoStoreInfoVO;

/**
 * @Class 	: MangoStoreInfoDAO.java
 * @Package	: com.yeollu.getrend.store.dao
 * @Project : GeTrend
 * @Author	: 조은채, 박민열
 * @Since	: 2020. 3. 26.
 * @Version	: 1.0
 * @Desc	: mango_stores 테이블에 접근하여 관련 작업을 수행한다.
 */
@Repository
public class MangoStoreInfoDAO {
	
	/**
	 * Fields
	 */
	@Autowired
	private SqlSession session;

	/**
	 * @Method	: selectMangoStoreInfoByStoreNo
	 * @Return	: MangoStoreInfoVO
	 * @Author	: 조은채, 박민열
	 * @Since	: 2020. 3. 26.
	 * @Version	: 1.0
	 * @Desc	: mango_stores 테이블에서 store_no로 데이터를 조회한다.
	 * @param store_no
	 */
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
	
	/**
	 * @Method	: selectMangoStoreInfoByStoreNoAndDays
	 * @Return	: MangoStoreInfoVO
	 * @Author	: 조은채, 박민열
	 * @Since	: 2020. 3. 26.
	 * @Version	: 1.0
	 * @Desc	: mango_stores 테이블에서 날짜와 시간으로 데이터를 조회한다.
	 * @param store_no
	 * @param opentimeValues
	 */
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
