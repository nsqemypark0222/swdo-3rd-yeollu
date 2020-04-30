package com.yeollu.getrend.user.vo;

import lombok.Data;

/**
 * @Class 	: FollowVO.java
 * @Package	: com.yeollu.getrend.user.vo
 * @Project : GeTrend
 * @Author	: 오선미, 조은채
 * @Since	: 2020. 3. 31.
 * @Version	: 1.0
 * @Desc	: follows 테이블의 VO 역할을 수행한다.
 */
@Data
public class FollowVO {
	private String user_email;
	private String follows_following;
	private String follow_indate;
}
