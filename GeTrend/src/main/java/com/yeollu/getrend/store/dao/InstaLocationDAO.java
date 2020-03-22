package com.yeollu.getrend.store.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.store.vo.InstaLocationVO;

@Repository
public class InstaLocationDAO {

	@Autowired
	private SqlSession session;
	
	public int insertInstaLocation(InstaLocationVO instaLocation) {
		int cnt = 0;
		
		try {
			InstaLocationMapper mapper = session.getMapper(InstaLocationMapper.class);
			cnt = mapper.insertInstaLocation(instaLocation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public boolean isExistedInstaLocationId(String store_no) {
		boolean result = false;
		
		try {
			InstaLocationMapper mapper = session.getMapper(InstaLocationMapper.class);
			if(mapper.isExistedInstaLocationId(store_no).equals("true")) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public InstaLocationVO selectInstaLocationById(String location_id) {
		InstaLocationVO instaLocation = null;
		
		try {
			InstaLocationMapper mapper = session.getMapper(InstaLocationMapper.class);
			instaLocation = mapper.selectInstaLocationById(location_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return instaLocation;
	}
}
