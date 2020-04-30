package com.yeollu.getrend.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yeollu.getrend.user.vo.UserVO;

/**
 * @Class 	: UserDAO.java
 * @Package	: com.yeollu.getrend.user.dao
 * @Project : GeTrend
 * @Author	: 오선미
 * @Since	: 2020. 3. 12.
 * @Version	: 1.0
 * @Desc	: users 테이블에 접근하여 작업을 수행한다.
 */
@Repository
public class UserDAO {
	/**
	 * Fields
	 */
	@Autowired
	private SqlSession session;
	
	/**
	 * @Method	: join
	 * @Return	: int
	 * @Author	: 오선미
	 * @Since	: 2020. 3. 12.
	 * @Version	: 1.0
	 * @Desc	: users 테이블에 데이터를 추가한다.
	 * @param user
	 */
	public int join(UserVO user) {
		int cnt = 0;
		
		if(user.getUser_profile() == null) {
			user.setUser_profile("https://res.cloudinary.com/dw5oh4ebf/image/upload/v1586757006/user_alnoxs.png");
			user.setUser_profileId("1586757006");
		}
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			cnt = mapper.join(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	/**
	 * @Method	: updateUser
	 * @Return	: int
	 * @Author	: 오선미
	 * @Since	: 2020. 3. 12.
	 * @Version	: 1.0
	 * @Desc	: users 테이블의 데이터를 갱신한다.
	 * @param user
	 */
	public int updateUser(UserVO user) {
		int cnt = 0;
		
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			cnt = mapper.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	/**
	 * @Method	: updateSocialUser
	 * @Return	: int
	 * @Author	: 오선미
	 * @Since	: 2020. 3. 12.
	 * @Version	: 1.0
	 * @Desc	: users 테이블의 데이터를 갱신한다.
	 * @param user
	 */
	public int updateSocialUser(UserVO user) {
		int cnt = 0;
		
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			cnt = mapper.updateSocialUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	/**
	 * @Method	: deleteUser
	 * @Return	: int
	 * @Author	: 오선미
	 * @Since	: 2020. 3. 12.
	 * @Version	: 1.0
	 * @Desc	: users 테이블에서 user_email로 삭제한다.
	 * @param user_email
	 */
	public int deleteUser(String user_email) {
		int cnt = 0;
		
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			cnt = mapper.deleteUser(user_email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	/**
	 * @Method	: selectEmail
	 * @Return	: UserVO
	 * @Author	: 오선미
	 * @Since	: 2020. 3. 12.
	 * @Version	: 1.0
	 * @Desc	: users 테이블에서 user_email로 조회한다.
	 * @param user_email
	 */
	public UserVO selectEmail(String user_email) {
		UserVO user = null;
		
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			user = mapper.selectEmail(user_email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	/**
	 * @Method	: selectName
	 * @Return	: UserVO
	 * @Author	: 오선미
	 * @Since	: 2020. 3. 18.
	 * @Version	: 1.0
	 * @Desc	: users 테이블에서 user_name으로 조회한다.
	 * @param user_name
	 */
	public UserVO selectName(String user_name){
		UserVO user = null;
		
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			user = mapper.selectName(user_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	/**
	 * @Method	: isExistedUserName
	 * @Return	: boolean
	 * @Author	: 오선미
	 * @Since	: 2020. 3. 12.
	 * @Version	: 1.0
	 * @Desc	: users 테이블에 데이터가 존재하는지 확인한다.
	 * @param user_name
	 */
	public boolean isExistedUserName(String user_name) {
		boolean result = false;
		
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			if(mapper.isExistedUserName(user_name).equals("true")) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
