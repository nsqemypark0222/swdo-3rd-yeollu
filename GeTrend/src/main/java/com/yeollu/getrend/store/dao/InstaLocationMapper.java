package com.yeollu.getrend.store.dao;

import com.yeollu.getrend.store.vo.InstaLocationVO;

public interface InstaLocationMapper {
	public int insertInstaLocation(InstaLocationVO instaLocation);
	public String isExistedInstaLocationId(String store_no);
	public InstaLocationVO selectInstaLocationById(String location_id);
}
