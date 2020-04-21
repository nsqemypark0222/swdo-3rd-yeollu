package com.yeollu.getrend.store.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.store.vo.InstaImageVO;

@Repository
public class InstaImageDAO {

	@Autowired
	private SqlSession session;
	
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
