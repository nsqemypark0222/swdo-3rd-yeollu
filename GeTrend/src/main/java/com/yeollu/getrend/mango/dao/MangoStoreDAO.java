package com.yeollu.getrend.mango.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.mango.vo.MangoDayVO;
import com.yeollu.getrend.mango.vo.MangoStoreVO;

/**
 * @Class 	: MangoStoreDAO.java
 * @Package	: com.yeollu.getrend.mango.dao
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 4. 19.
 * @Version	: 1.0
 * @Desc	: mango_stores 테이블에 접근하여 작업을 수행한다.
 */
@Repository
public class MangoStoreDAO {
	
	/**
	 * Fields
	 */
	@Autowired
	private SqlSession session;
	
	/**
	 * @Method	: insertMangoStore
	 * @Return	: int
	 * @Author	: 박민열
	 * @Since	: 2020. 4. 19.
	 * @Version	: 1.0
	 * @Desc	: mango_stores 테이블에 데이터를 추가한다.
	 * @param mangoStore
	 */
	public int insertMangoStore(MangoStoreVO mangoStore) {
		int cnt = 0;
		
		try {
			MangoStoreMapper mapper = session.getMapper(MangoStoreMapper.class);
			cnt = mapper.insertMangoStore(mangoStore);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	/**
	 * @Method	: selectAllMangoStores
	 * @Return	: ArrayList<MangoStoreVO>
	 * @Author	: 박민열
	 * @Since	: 2020. 4. 19.
	 * @Version	: 1.0
	 * @Desc	: mango_stores 테이블에서 모든 데이터를 조회한다.
	 */
	public ArrayList<MangoStoreVO> selectAllMangoStores() {
		ArrayList<MangoStoreVO> list = new ArrayList<MangoStoreVO>();
		
		try {
			MangoStoreMapper mapper = session.getMapper(MangoStoreMapper.class);
			list = mapper.selectAllMangoStores();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * @Method	: selectMangoStoreByStoreNo
	 * @Return	: MangoStoreVO
	 * @Author	: 박민열
	 * @Since	: 2020. 4. 19.
	 * @Version	: 1.0
	 * @Desc	: mango_stores 테이블에서 데이터를 조회한다.
	 * @param store_no
	 */
	public MangoStoreVO selectMangoStoreByStoreNo(String store_no) {
		MangoStoreVO mangoStore = null;
		
		try {
			MangoStoreMapper mapper = session.getMapper(MangoStoreMapper.class);
			mangoStore = mapper.selectMangoStoreByStoreNo(store_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mangoStore;
	}
	
	/**
	 * @Method	: selectMangoStoreByMangoDay
	 * @Return	: ArrayList<MangoStoreVO>
	 * @Author	: 박민열
	 * @Since	: 2020. 4. 19.
	 * @Version	: 1.0
	 * @Desc	: mango_stores 테이블에서 데이터를 조회한다.
	 * @param mangoDay
	 * @return
	 */
	public ArrayList<MangoStoreVO> selectMangoStoreByMangoDay(MangoDayVO mangoDay) {
		ArrayList<MangoStoreVO> list = new ArrayList<MangoStoreVO>();
		
		try {
			MangoStoreMapper mapper = session.getMapper(MangoStoreMapper.class);
			list = mapper.selectMangoStoreByMangoDay(mangoDay);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * @Method	: updateMangoStore
	 * @Return	: int
	 * @Author	: 박민열
	 * @Since	: 2020. 4. 19.
	 * @Version	: 1.0
	 * @Desc	: mango_stores 테이블에서 데이터를 갱신한다.
	 * @param mangoStore
	 */
	public int updateMangoStore(MangoStoreVO mangoStore) {
		int cnt = 0;
		
		try {
			MangoStoreMapper mapper = session.getMapper(MangoStoreMapper.class);
			cnt = mapper.updateMangoStore(mangoStore);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
}
