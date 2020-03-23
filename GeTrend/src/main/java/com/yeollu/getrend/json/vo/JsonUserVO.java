package com.yeollu.getrend.json.vo;

import lombok.Data;

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
