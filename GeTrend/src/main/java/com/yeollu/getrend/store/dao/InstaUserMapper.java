package com.yeollu.getrend.store.dao;

import com.yeollu.getrend.store.vo.InstaUserVO;

public interface InstaUserMapper {
	public int insertInstaUser(InstaUserVO instaUser);
	public String isExistedInstaUserId(String store_no);
}
