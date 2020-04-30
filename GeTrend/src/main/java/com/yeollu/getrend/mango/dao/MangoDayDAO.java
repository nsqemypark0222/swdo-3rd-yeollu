package com.yeollu.getrend.mango.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.mango.vo.MangoDayVO;

/**
 * @Class 	: MangoDayDAO.java
 * @Package	: com.yeollu.getrend.mango.dao
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 4. 19.
 * @Version	: 1.0
 * @Desc	: mango_days 테이블에 접근하여 작업을 수행한다.
 */
@Repository
public class MangoDayDAO {

	/**
	 * Fields
	 */
	@Autowired
	private SqlSession session;
	
	/**
	 * @Method	: insertMangoDay
	 * @Return	: int
	 * @Author	: 박민열
	 * @Since	: 2020. 4. 19.
	 * @Version	: 1.0
	 * @Desc	: mango_days 테이블에 데이터를 추가한다.
	 * @param mangoDay
	 */
	public int insertMangoDay(MangoDayVO mangoDay) {
		int cnt = 0;
		
		try {
			MangoDayMapper mapper = session.getMapper(MangoDayMapper.class);
			cnt = mapper.insertMangoDay(mangoDay);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	/**
	 * @Method	: updateMangoDay
	 * @Return	: int
	 * @Author	: 박민열
	 * @Since	: 2020. 4. 19.
	 * @Version	: 1.0
	 * @Desc	: mango_days 테이블의 데이터를 갱신한다.
	 * @param mangoDay
	 */
	public int updateMangoDay(MangoDayVO mangoDay) {
		int cnt = 0;
		
		try {
			MangoDayMapper mapper = session.getMapper(MangoDayMapper.class);
			cnt = mapper.updateMangoDay(mangoDay);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
}
