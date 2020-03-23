package com.yeollu.getrend.store.dao;

import java.util.ArrayList;

import com.yeollu.getrend.store.vo.InstaLocationVO;

public interface InstaLocationMapper {
	public int insertInstaLocation(InstaLocationVO instaLocation);
	public int insertInstaLocationList(ArrayList<InstaLocationVO> instaLocationList);
	public String isExistedInstaLocationId(String store_no);
	public InstaLocationVO selectInstaLocationById(String location_id);
}
