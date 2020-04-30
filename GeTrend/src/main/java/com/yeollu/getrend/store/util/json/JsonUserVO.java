package com.yeollu.getrend.store.util.json;

import lombok.Data;

/**
 * @Class 	: JsonUserVO.java
 * @Package	: com.yeollu.getrend.store.util.json
 * @Project : GeTrend
 * @Author	: 조은채, 박민열
 * @Since	: 2020. 3. 26.
 * @Version	: 1.0
 * @Desc	: 인스타그램으로부터 받은 결과값에서 JSON 형식의 user 정보에 대한 VO 역할을 수행한다. 
 */
@Data
public class JsonUserVO {
	private String pk;
	private String username;
	private String full_name;
	private boolean is_private;
	private String profile_pic_url;
	private String profile_pic_id;
	private boolean is_verified;
	private boolean has_anonymous_profile_picture;
	private int mutual_followers_count;
	private int latest_reel_media;
}
