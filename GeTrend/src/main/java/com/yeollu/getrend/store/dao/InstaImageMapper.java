package com.yeollu.getrend.store.dao;

import java.util.ArrayList;

import com.yeollu.getrend.store.vo.InstaImageVO;

public interface InstaImageMapper {
	public int insertInstaImage(InstaImageVO instaImage);
	
	public int updateInstaIamge(InstaImageVO instaImage);
	
	public String isExistedInstaImage(String store_no);
	
	public String isRequiredUpdateInstaImage(String store_no);
	
	public ArrayList<InstaImageVO> selectInstaImageByStoreNo(String store_no);
}
