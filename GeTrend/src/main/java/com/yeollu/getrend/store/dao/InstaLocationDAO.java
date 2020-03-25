package com.yeollu.getrend.store.dao;

import java.util.ArrayList;

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
	
	public int insertInstaLocationList(ArrayList<InstaLocationVO> instaLocationList) {
		int cnt = 0;
		
		try {
			InstaLocationMapper mapper = session.getMapper(InstaLocationMapper.class);
			cnt = mapper.insertInstaLocationList(instaLocationList);
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
	
	public boolean isExistedInstaLocation(String store_no) {
		boolean result = false;
		
		try {
			InstaLocationMapper mapper = session.getMapper(InstaLocationMapper.class);
			if(mapper.isExistedInstaLocation(store_no).equals("true")) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public InstaLocationVO selectInstaLocationByPk(String location_pk) {
		InstaLocationVO instaLocation = null;
		
		try {
			InstaLocationMapper mapper = session.getMapper(InstaLocationMapper.class);
			instaLocation = mapper.selectInstaLocationByPk(location_pk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return instaLocation;
	}
}
