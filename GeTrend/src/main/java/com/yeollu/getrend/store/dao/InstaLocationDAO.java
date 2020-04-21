package com.yeollu.getrend.store.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.store.vo.InstaLocationVO;

@Repository
public class InstaLocationDAO {

	@Autowired
	private SqlSession session;
	
	public int insertInstaLocation(InstaLocationVO location_id) {
		int cnt = 0;
		
		try {
			InstaLocationMapper mapper = session.getMapper(InstaLocationMapper.class);
			cnt = mapper.insertInstaLocation(location_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public int countInstaLocations() {
		int count = 0;
		
		try {
			InstaLocationMapper mapper = session.getMapper(InstaLocationMapper.class);
			count = mapper.countInstaLocations();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	public boolean isExistedInstaLocation(String location_id) {
		boolean result = false;
		
		try {
			InstaLocationMapper mapper = session.getMapper(InstaLocationMapper.class);
			if(mapper.isExistedInstaLocation(location_id).equals("true")) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public InstaLocationVO selectLocationByStoreNo(String store_no) {
		InstaLocationVO instaLocation = new InstaLocationVO();
		
		try {
			InstaLocationMapper mapper = session.getMapper(InstaLocationMapper.class);
			instaLocation = mapper.selectLocationByStoreNo(store_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return instaLocation;
	}
}
