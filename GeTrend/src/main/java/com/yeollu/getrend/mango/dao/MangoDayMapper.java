package com.yeollu.getrend.mango.dao;

import com.yeollu.getrend.mango.vo.MangoDayVO;

/**
 * @Class 	: MangoDayMapper.java
 * @Package	: com.yeollu.getrend.mango.dao
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 4. 19.
 * @Version	: 1.0
 * @Desc	: mango_days 테이블의 Mapper interface 역할을 수행한다.
 */
public interface MangoDayMapper {
	public int insertMangoDay(MangoDayVO mangoDay);
	public int updateMangoDay(MangoDayVO mangoDay);
}
