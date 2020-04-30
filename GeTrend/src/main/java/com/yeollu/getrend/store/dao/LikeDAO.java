package com.yeollu.getrend.store.dao;


import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.store.vo.LikeVO;


/**
 * @Class 	: LikeDAO.java
 * @Package	: com.yeollu.getrend.store.dao
 * @Project : GeTrend
 * @Author	: 조은채
 * @Since	: 2020. 4. 11.
 * @Version	: 1.0
 * @Desc	: likes 테이블에 접근하여 관련 작업을 수행한다.
 */
@Repository
public class LikeDAO {

	/**
	 * Fields
	 */
	@Autowired
	private SqlSession session;
	
	/**
	 * @Method	: likeInsert
	 * @Return	: int
	 * @Author	: 조은채
	 * @Since	: 2020. 4. 11.
	 * @Version	: 1.0
	 * @Desc	: likes 테이블에 데이터를 추가한다.
	 * @param like
	 */
	public int likeInsert(LikeVO like) {
		int cnt = 0;
		
		try {
			LikeMapper mapper = session.getMapper(LikeMapper.class);
			cnt = mapper.likeInsert(like);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	/**
	 * @Method	: likeDelete
	 * @Return	: int
	 * @Author	: 조은채
	 * @Since	: 2020. 4. 11.
	 * @Version	: 1.0
	 * @Desc	: likes 테이블에서 데이터를 삭제한다.
	 * @param like
	 */
	public int likeDelete(LikeVO like) {
		int cnt = 0;
		
		try {
			LikeMapper mapper = session.getMapper(LikeMapper.class);
			cnt = mapper.likeDelete(like);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	/**
	 * @Method	: likeSelectByEmail
	 * @Return	: ArrayList<HashMap<String,Object>>
	 * @Author	: 조은채
	 * @Since	: 2020. 4. 11.
	 * @Version	: 1.0
	 * @Desc	: likes 테이블에서 user_email로 데이터를 조회한다.
	 * @param user_email
	 */
	public ArrayList<HashMap<String, Object>> likeSelectByEmail(String user_email){
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		
		try {
			LikeMapper mapper = session.getMapper(LikeMapper.class);
			list = mapper.likeSelectByEmail(user_email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * @Method	: likeSelectByEmailStoreno
	 * @Return	: int
	 * @Author	: 조은채
	 * @Since	: 2020. 4. 11.
	 * @Version	: 1.0
	 * @Desc	: likes 테이블에서 데이터를 조회한다.
	 * @param like
	 */
	public int likeSelectByEmailStoreno(LikeVO like){
		int cnt = 0;
		try {
			LikeMapper mapper = session.getMapper(LikeMapper.class);
			cnt = mapper.likeSelectByEmailStoreno(like);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return cnt;
	}
	
	/**
	 * @Method	: likeStoreCountByEmail
	 * @Return	: int
	 * @Author	: 조은채
	 * @Since	: 2020. 4. 11.
	 * @Version	: 1.0
	 * @Desc	: likes 테이블에서 user_email로 좋아요 수를 조회한다.
	 * @param user_email
	 */
	public int likeStoreCountByEmail(String user_email){
			int cnt = 0;
		try {
			LikeMapper mapper = session.getMapper(LikeMapper.class);
			cnt = mapper.likeStoreCountByEmail(user_email);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return cnt;
	}
	
	/**
	 * @Method	: isExistedLike
	 * @Return	: boolean
	 * @Author	: 조은채
	 * @Since	: 2020. 4. 11.
	 * @Version	: 1.0
	 * @Desc	: likes 테이블에서 데이터 존재 여부를 확인한다.
	 * @param like
	 */
	public boolean isExistedLike(LikeVO like) {
		boolean result = false;
		try {
			LikeMapper mapper = session.getMapper(LikeMapper.class);
			if(mapper.isExistedLike(like).equals("true")) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
