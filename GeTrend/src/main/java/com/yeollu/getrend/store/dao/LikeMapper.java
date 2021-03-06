package com.yeollu.getrend.store.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.yeollu.getrend.store.vo.LikeVO;

/**
 * @Class 	: LikeMapper.java
 * @Package	: com.yeollu.getrend.store.dao
 * @Project : GeTrend
 * @Author	: 조은채
 * @Since	: 2020. 4. 11.
 * @Version	: 1.0
 * @Desc	: likes 테이블의 Mapper interface 역할을 수행한다
 */
public interface LikeMapper {
	public int likeInsert(LikeVO like);//가게 좋아요
	public int likeDelete(LikeVO like);//가게 좋아요 취소
	public ArrayList<HashMap<String, Object>> likeSelectByEmail(String user_email);//가게리스트 모달
	public int likeSelectByEmailStoreno(LikeVO like);//좋아요한 가게인지 체크
	public int likeStoreCountByEmail(String user_email);//좋아하는 가게수
	public String isExistedLike(LikeVO like);
}
