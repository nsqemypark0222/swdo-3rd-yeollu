package com.yeollu.getrend.store.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.store.vo.InstaLocationVO;

/**
 * @Class 	: InstaLocationDAO.java
 * @Package	: com.yeollu.getrend.store.dao
 * @Project : GeTrend
 * @Author	: 조은채, 박민열
 * @Since	: 2020. 3. 26.
 * @Version	: 1.0
 * @Desc	: insta_locations 테이블에 접근하여 관련 작업을 수행한다.
 */
@Repository
public class InstaLocationDAO {

	/**
	 * Fields
	 */
	@Autowired
	private SqlSession session;
	
	/**
	 * @Method	: insertInstaLocation
	 * @Return	: int
	 * @Author	: 조은채, 박민열
	 * @Since	: 2020. 3. 26.
	 * @Version	: 1.0
	 * @Desc	: insta_locations 테이블에 데이터를 추가한다.
	 * @param location_id
	 */
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
	
	/**
	 * @Method	: countInstaLocations
	 * @Return	: int
	 * @Author	: 조은채, 박민열
	 * @Since	: 2020. 3. 26.
	 * @Version	: 1.0
	 * @Desc	: insta_locations 테이블의 데이터 수를 조회한다.
	 */
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
	
	/**
	 * @Method	: isExistedInstaLocation
	 * @Return	: boolean
	 * @Author	: 조은채, 박민열
	 * @Since	: 2020. 3. 26.
	 * @Version	: 1.0
	 * @Desc	: insta_locations 테이블에 존재하는 데이터인지 여부를 조회한다.
	 * @param location_id
	 */
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
	
	/**
	 * @Method	: selectLocationByStoreNo
	 * @Return	: InstaLocationVO
	 * @Author	: 조은채, 박민열
	 * @Since	: 2020. 3. 26.
	 * @Version	: 1.0
	 * @Desc	: insta_locations 테이블에서 store_no로 데이터를 조회한다.
	 * @param store_no
	 */
	public InstaLocationVO selectLocationByStoreNo(String store_no) {
		InstaLocationVO instaLocation = null;
		
		try {
			InstaLocationMapper mapper = session.getMapper(InstaLocationMapper.class);
			instaLocation = mapper.selectLocationByStoreNo(store_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return instaLocation;
	}
}
