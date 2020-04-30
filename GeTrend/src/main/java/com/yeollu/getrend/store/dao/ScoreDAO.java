package com.yeollu.getrend.store.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Class 	: ScoreDAO.java
 * @Package	: com.yeollu.getrend.store.dao
 * @Project : GeTrend
 * @Author	: 조은채, 박민열
 * @Since	: 2020. 4. 11.
 * @Version	: 1.0
 * @Desc	: scores 테이블에 접근하여 관련 작업을 수행한다.
 */
@Repository
public class ScoreDAO {
	
	/**
	 * Fields
	 */
	@Autowired
	private SqlSession session;
	
	/**
	 * @Method	: selectCountLikeByStoreNo
	 * @Return	: int
	 * @Author	: 조은채, 박민열
	 * @Since	: 2020. 4. 11.
	 * @Version	: 1.0
	 * @Desc	: 가게의 좋아요 수를 조회한다.
	 * @param store_no
	 */
	public int selectCountLikeByStoreNo(String store_no) {
		int cnt = 0;
		try {
			ScoreMapper mapper = session.getMapper(ScoreMapper.class);
			cnt = mapper.selectCountLikeByStoreNo(store_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	/**
	 * @Method	: scoreAvgByStoreno
	 * @Return	: double
	 * @Author	: 조은채, 박민열
	 * @Since	: 2020. 4. 11.
	 * @Version	: 1.0
	 * @Desc	: 가게의 평균 별점을 조회한다.
	 * @param store_no
	 */
	public double scoreAvgByStoreNo(String store_no)  {
		double avg = 0;
		try {
			ScoreMapper mapper = session.getMapper(ScoreMapper.class);
			avg = mapper.scoreAvgByStoreNo(store_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return avg;
	}
}
