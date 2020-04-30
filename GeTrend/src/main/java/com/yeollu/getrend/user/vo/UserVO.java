package com.yeollu.getrend.user.vo;

import lombok.Data;

/**
 * @Class 	: UserVO.java
 * @Package	: com.yeollu.getrend.user.vo
 * @Project : GeTrend
 * @Author	: 오선미
 * @Since	: 2020. 3. 12.
 * @Version	: 1.0
 * @Desc	: users 테이블의 VO 역할을 수행한다.
 */
@Data
public class UserVO {
	private String user_email;
	private String user_pw;
	private String user_name;
	private String user_type;
	private String user_profile;
	private String user_profileId;
}
