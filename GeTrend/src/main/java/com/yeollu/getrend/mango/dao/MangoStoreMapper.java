package com.yeollu.getrend.mango.dao;

import java.util.ArrayList;

import com.yeollu.getrend.mango.vo.MangoDayVO;
import com.yeollu.getrend.mango.vo.MangoStoreVO;

/**
 * @Class 	: MangoStoreMapper.java
 * @Package	: com.yeollu.getrend.mango.dao
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 4. 19
 * @Version	: 1.0
 * @Desc	: mango_stores 테이블의 Mapper interface 역할을 수행한다.
 */
public interface MangoStoreMapper {
	public int insertMangoStore(MangoStoreVO mangoStore);
	public ArrayList<MangoStoreVO> selectAllMangoStores();
	public MangoStoreVO selectMangoStoreByStoreNo(String store_no);
	public ArrayList<MangoStoreVO> selectMangoStoreByMangoDay(MangoDayVO mangoDay);
	public int updateMangoStore(MangoStoreVO mangoStore);
}
