package com.yeollu.getrend.mango.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.mango.vo.MangoTimeVO;

/**
 * @Class 	: MangoTimeDAO.java
 * @Package	: com.yeollu.getrend.mango.dao
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 4. 19.
 * @Version	: 1.0
 * @Desc	: mango_times 테이블에 접근하여 작업을 수행한다.
 */
@Repository
public class MangoTimeDAO {
	
	/**
	 * Fields
	 */
	@Autowired
	private SqlSession session;
	
	/**
	 * @Method	: insertMangoTime
	 * @Return	: int
	 * @Author	: 박민열
	 * @Since	: 2020. 4. 19.
	 * @Version	: 1.0
	 * @Desc	: mango_times 테이블에 데이터를 추가한다.
	 * @param mangoTime
	 * @return
	 */
	public int insertMangoTime(MangoTimeVO mangoTime) {
		int cnt = 0;
		try {
			MangoTimeMapper mapper = session.getMapper(MangoTimeMapper.class);
			cnt = mapper.insertMangoTime(mangoTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	/**
	 * @Method	: updateMangoTime
	 * @Return	: int
	 * @Author	: 박민열
	 * @Since	: 2020. 4. 19.
	 * @Version	: 1.0
	 * @Desc	: mango_times 테이블의 데이터를 갱신한다.
	 * @param mangoTime
	 * @return
	 */
	public int updateMangoTime(MangoTimeVO mangoTime) {
		int cnt = 0;
		try {
			MangoTimeMapper mapper = session.getMapper(MangoTimeMapper.class);
			cnt = mapper.updateMangoTime(mangoTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
}
