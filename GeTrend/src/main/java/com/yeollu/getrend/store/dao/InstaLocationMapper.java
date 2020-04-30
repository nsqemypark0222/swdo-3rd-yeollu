package com.yeollu.getrend.store.dao;

import com.yeollu.getrend.store.vo.InstaLocationVO;

/**
 * @Class 	: InstaLocationMapper.java
 * @Package	: com.yeollu.getrend.store.dao
 * @Project : GeTrend
 * @Author	: 조은채, 박민열
 * @Since	: 2020. 3. 26.
 * @Version	: 1.0
 * @Desc	: insta_locations 테이블의 Mapper interface 역할을 수행한다
 */
public interface InstaLocationMapper {
	public int insertInstaLocation(InstaLocationVO location_id);
	public int countInstaLocations();
	public String isExistedInstaLocation(String location_id);
	public InstaLocationVO selectLocationByStoreNo(String store_no);
}
