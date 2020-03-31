package com.yeollu.getrend.store.dao;

import java.util.ArrayList;

import com.yeollu.getrend.store.vo.LikeVO;

public interface LikeMapper {
	public int likeInsert(LikeVO like);
	public int likeDelete(LikeVO like);
	public ArrayList<LikeVO> likeSelectByEmail(String user_email);
	public int likeStoreCountByEmail(String user_email);
	public ArrayList<LikeVO> likeSelectByStoreno(String store_no);
}
