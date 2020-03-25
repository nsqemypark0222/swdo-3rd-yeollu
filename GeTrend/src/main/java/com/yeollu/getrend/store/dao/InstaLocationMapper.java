package com.yeollu.getrend.store.dao;

import java.util.ArrayList;

import com.yeollu.getrend.store.vo.InstaLocationVO;

public interface InstaLocationMapper {
	public int insertInstaLocation(InstaLocationVO instaLocation);
	public int insertInstaLocationList(ArrayList<InstaLocationVO> instaLocationList);
	public int countInstaLocations();
	public String isExistedInstaLocation(String store_no);
	public InstaLocationVO selectInstaLocationByPk(String location_pk);
}
