package com.yeollu.getrend.store.dao;

import java.util.HashMap;

import com.yeollu.getrend.store.vo.MangoStoreInfoVO;

/**
 * @Class 	: MangoStoreInfoMapper.java
 * @Package	: com.yeollu.getrend.store.dao
 * @Project : GeTrend
 * @Author	: 조은채, 박민열
 * @Since	: 2020. 3. 26.
 * @Version	: 1.0
 * @Desc	: mango_stores 테이블의 Mapper interface 역할을 수행한다
 */
public interface MangoStoreInfoMapper {
	public MangoStoreInfoVO selectMangoStoreInfoByStoreNo(String store_no);
	public MangoStoreInfoVO selectMangoStoreInfoByStoreNoAndDays(HashMap<String, String> map);
}
