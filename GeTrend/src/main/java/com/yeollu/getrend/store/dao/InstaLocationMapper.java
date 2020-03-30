package com.yeollu.getrend.store.dao;

import com.yeollu.getrend.store.vo.InstaLocationVO;

public interface InstaLocationMapper {
	public int insertInstaLocation(InstaLocationVO location_id);
	public int countInstaLocations();
	public String isExistedInstaLocation(String location_id);
	public InstaLocationVO selectLocationByStoreNo(String store_no);
}
