package com.yeollu.getrend.store.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.store.vo.RecommendVO;

/**
 * @Class 	: RecommendDAO.java
 * @Package	: com.yeollu.getrend.store.dao
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 4. 19.
 * @Version	: 1.0
 * @Desc	: recommend 테이블에 접근하여 관련 작업을 수행한다.
 */
@Repository
public class RecommendDAO {

	/**
	 * Fields
	 */
	@Autowired
	private SqlSession session;
	
	/**
	 * @Method	: insertRecommend
	 * @Return	: int
	 * @Author	: 박민열
	 * @Since	: 2020. 4. 19.
	 * @Version	: 1.0
	 * @Desc	: recommend 테이블에 데이터를 추가한다.
	 * @param recommend
	 */
	public int insertRecommend(RecommendVO recommend) {
		int cnt = 0;
		
		try {
			RecommendMapper mapper = session.getMapper(RecommendMapper.class);
			cnt = mapper.insertRecommend(recommend);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	/**
	 * @Method	: selectAllRecommend
	 * @Return	: ArrayList<RecommendVO>
	 * @Author	: 박민열
	 * @Since	: 2020. 4. 19.
	 * @Version	: 1.0
	 * @Desc	: recommend 테이블의 모든 데이터를 조회한다.
	 */
	public ArrayList<RecommendVO> selectAllRecommend() {
		ArrayList<RecommendVO> list = null;
		
		try {
			RecommendMapper mapper = session.getMapper(RecommendMapper.class);
			list = mapper.selectAllRecommend();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * @Method	: selectRecommendByStoreAdr
	 * @Return	: ArrayList<RecommendVO>
	 * @Author	: 박민열
	 * @Since	: 2020. 4. 19.
	 * @Version	: 1.0
	 * @Desc	: recommend 테이블에서 store_adr로 데이터를 조회한다.
	 * @param store_adr
	 */
	public ArrayList<RecommendVO> selectRecommendByStoreAdr(String store_adr) {
		ArrayList<RecommendVO> list = null;
		
		try {
			RecommendMapper mapper = session.getMapper(RecommendMapper.class);
			list = mapper.selectRecommendByStoreAdr(store_adr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * @Method	: selectRecommendByStoreCate1
	 * @Return	: ArrayList<RecommendVO>
	 * @Author	: 박민열
	 * @Since	: 2020. 4. 19.
	 * @Version	: 1.0
	 * @Desc	: recommend 테이블에서 store_cate1로 데이터를 조회한다.
	 * @param store_cate1
	 */
	public ArrayList<RecommendVO> selectRecommendByStoreCate1(String store_cate1) {
		ArrayList<RecommendVO> list = null;
		
		try {
			RecommendMapper mapper = session.getMapper(RecommendMapper.class);
			list = mapper.selectRecommendByStoreCate1(store_cate1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * @Method	: selectRecommendStore
	 * @Return	: ArrayList<HashMap<String,Object>>
	 * @Author	: 박민열
	 * @Since	: 2020. 4. 19.
	 * @Version	: 1.0
	 * @Desc	: recommend 테이블에서 store_adr로 데이터를 조회한다.
	 * @param store_adr
	 */
	public ArrayList<HashMap<String, Object>> selectRecommendStore(String store_adr) {
		ArrayList<HashMap<String, Object>> resultMapList = null;
		
		try {
			RecommendMapper mapper = session.getMapper(RecommendMapper.class);
			resultMapList = mapper.selectRecommendStore(store_adr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultMapList;
	}
}
