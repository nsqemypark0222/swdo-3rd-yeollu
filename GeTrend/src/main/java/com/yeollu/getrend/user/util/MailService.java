package com.yeollu.getrend.user.util;

/**
 * @Class 	: MailService.java
 * @Package	: com.yeollu.getrend.user.util
 * @Project : GeTrend
 * @Author	: 오선미
 * @Since	: 2020. 3. 21.
 * @Version	: 1.0
 * @Desc	: 메일 서비스를 위한 interface 역할을 수행한다.
 */
public interface MailService {
	public boolean send(String subject, String text, String from, String to, String filePath);
}


