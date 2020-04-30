package com.yeollu.getrend.store.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.store.vo.InstaImageVO;

/**
 * @Class 	: InstaImageDAO.java
 * @Package	: com.yeollu.getrend.store.dao
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 3. 26.
 * @Version	: 1.0
 * @Desc	: insta_images 테이블에 접근하여 관련 작업을 수행한다.
 */
@Repository
public class InstaImageDAO {

	/**
	 * Fields
	 */
	@Autowired
	private SqlSession session;
	
	/**
	 * @Method	: insertInstaImage
	 * @Return	: int
	 * @Author	: 박민열
	 * @Since	: 2020. 3. 26.
	 * @Version	: 1.0
	 * @Desc	: insta_images 테이블에 데이터를 추가한다.
	 * @param instaImage
	 */
	public int insertInstaImage(InstaImageVO instaImage) {
		int cnt = 0;
		
		try {
			InstaImageMapper mapper = session.getMapper(InstaImageMapper.class);
			cnt = mapper.insertInstaImage(instaImage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	/**
	 * @Method	: updateInstaIamge
	 * @Return	: int
	 * @Author	: 박민열
	 * @Since	: 2020. 3. 26.
	 * @Version	: 1.0
	 * @Desc	: insta_images 테이블의 데이터를 갱신한다.
	 * @param instaImage
	 */
	public int updateInstaIamge(InstaImageVO instaImage) {
		int cnt = 0;
		
		try {
			InstaImageMapper mapper = session.getMapper(InstaImageMapper.class);
			cnt = mapper.updateInstaIamge(instaImage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	/**
	 * @Method	: isExistedInstaImage
	 * @Return	: boolean
	 * @Author	: 박민열
	 * @Since	: 2020. 3. 26.
	 * @Version	: 1.0
	 * @Desc	: insta_images 테이블에 데이터의 존재여부를 조회한다.
	 * @param store_no
	 */
	public boolean isExistedInstaImage(String store_no) {
		boolean result = false;
		
		try {
			InstaImageMapper mapper = session.getMapper(InstaImageMapper.class);
			if(mapper.isExistedInstaImage(store_no).equals("true")) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * @Method	: isRequiredUpdateInstaImage
	 * @Return	: boolean
	 * @Author	: 박민열
	 * @Since	: 2020. 3. 26.
	 * @Version	: 1.0
	 * @Desc	: insta_images 테이블의 데이터가 업데이트 필요성이 있는지를 조회한다.
	 * @param store_no
	 */
	public boolean isRequiredUpdateInstaImage(String store_no) {
		boolean result = false;
		
		try {
			InstaImageMapper mapper = session.getMapper(InstaImageMapper.class);
			if(mapper.isRequiredUpdateInstaImage(store_no).equals("true")) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * @Method	: selectInstaImageByStoreNo
	 * @Return	: ArrayList<InstaImageVO>
	 * @Author	: 박민열
	 * @Since	: 2020. 3. 26.
	 * @Version	: 1.0
	 * @Desc	: insta_images 테이블에서 store_no로 조회한다.
	 * @param store_no
	 */
	public ArrayList<InstaImageVO> selectInstaImageByStoreNo(String store_no) {
		ArrayList<InstaImageVO> list = null;
		
		try {
			InstaImageMapper mapper = session.getMapper(InstaImageMapper.class);
			list = mapper.selectInstaImageByStoreNo(store_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * @Method	: deleteInstaImage
	 * @Return	: int
	 * @Author	: 박민열
	 * @Since	: 2020. 3. 26.
	 * @Version	: 1.0
	 * @Desc	: insta_images 테이블에서 데이터를 삭제한다.
	 * @param store_no
	 */
	public int deleteInstaImage(String store_no) {
		int cnt = 0;
		
		try {
			InstaImageMapper mapper = session.getMapper(InstaImageMapper.class);
			cnt = mapper.deleteInstaImage(store_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
}
