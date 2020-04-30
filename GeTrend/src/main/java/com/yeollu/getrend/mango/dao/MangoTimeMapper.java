package com.yeollu.getrend.mango.dao;

import com.yeollu.getrend.mango.vo.MangoTimeVO;

/**
 * @Class 	: MangoTimeMapper.java
 * @Package	: com.yeollu.getrend.mango.dao
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 4. 19
 * @Version	: 1.0
 * @Desc	: mango_times 테이블의 Mapper interface 역할을 수행한다.
 */
public interface MangoTimeMapper {
	public int insertMangoTime(MangoTimeVO mangoTime);
	public int updateMangoTime(MangoTimeVO mangoTime);
}
