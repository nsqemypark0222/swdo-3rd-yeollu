package com.yeollu.getrend.store.vo;

import lombok.Data;

/**
 * @Class 	: InstaReplyVO.java
 * @Package	: com.yeollu.getrend.store.vo
 * @Project : GeTrend
 * @Author	: 오선미, 조은채
 * @Since	: 2020. 3. 26.
 * @Version	: 1.0
 * @Desc	: insta_replys 테이블의 VO 역할을 수행한다.
 */
@Data
public class InstaReplyVO {
    private String reply_no;
	private String store_no;
	private String user_email;
	private String reply_contents;
	private String reply_star;
	private String reply_indate;
}
