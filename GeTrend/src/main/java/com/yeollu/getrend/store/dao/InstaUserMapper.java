package com.yeollu.getrend.store.dao;

import java.util.ArrayList;

import com.yeollu.getrend.store.vo.InstaUserVO;

public interface InstaUserMapper {
	public int insertInstaUser(InstaUserVO instaUser);
	public int insertInstaUserList(ArrayList<InstaUserVO> instaUserList);
	public String isExistedInstaUserId(String store_no);
	public InstaUserVO selectInstaUserById(String insta_id);
}
