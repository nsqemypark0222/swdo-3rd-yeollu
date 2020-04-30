package com.yeollu.getrend.store.dao;

import java.util.ArrayList;

import com.yeollu.getrend.store.vo.InstaImageVO;

/**
 * @Class 	: InstaImageMapper.java
 * @Package	: com.yeollu.getrend.store.dao
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 3. 26.
 * @Version	: 1.0
 * @Desc	: insta_images 테이블의 Mapper interface 역할을 수행한다
 */
public interface InstaImageMapper {
	public int insertInstaImage(InstaImageVO instaImage);
	public int updateInstaIamge(InstaImageVO instaImage);
	public String isExistedInstaImage(String store_no);
	public String isRequiredUpdateInstaImage(String store_no);
	public ArrayList<InstaImageVO> selectInstaImageByStoreNo(String store_no);
	public int deleteInstaImage(String store_no);
}
