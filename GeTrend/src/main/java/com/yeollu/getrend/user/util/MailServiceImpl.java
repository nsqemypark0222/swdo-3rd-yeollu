package com.yeollu.getrend.user.util;

import java.io.File;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * @Class 	: MailServiceImpl.java
 * @Package	: com.yeollu.getrend.user.util
 * @Project : GeTrend
 * @Author	: 오선미
 * @Since	: 2020. 3. 21.
 * @Version	: 1.0
 * @Desc	: 회원 가입을 위한 인증 코드를 담은 이메일을 송신한다.
 */
@Service
public class MailServiceImpl implements MailService {
	
	/**
	 * Fields
	 */
	@Inject
    private JavaMailSender javaMailSender;
 
    /**
     * @Method	: setJavaMailSender
     * @Return	: void
     * @Author	: 오선미
     * @Since	: 2020. 3. 21.
     * @Version	: 1.0
     * @Desc	: Setter
     * @param javaMailSender
     */
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
 
    /**
     * Overriding
     * @Method	: send
     * @Return	: boolean
     * @Author	: 오선미
     * @Since	: 2020. 3. 21.
     * @Version	: 1.0
     * @Desc	: 텍스트와 파일을 매개변수로 넘겨받아 이메일을 송신한다.
     * @param subject
     * @param text
     * @param from
     * @param to
     * @param filePath
     */
    @Override
    public boolean send(String subject, String text, String from, String to, String filePath) {
        // javax.mail.internet.MimeMessage
        MimeMessage message = javaMailSender.createMimeMessage();
 
        try {
            // org.springframework.mail.javamail.MimeMessageHelper
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setSubject(subject);
            helper.setText(text, true);
            helper.setFrom(from);
            helper.setTo(to);
 
            // 첨부 파일 처리
            if (filePath != null) {
                File file = new File(filePath);
                if (file.exists()) {
                    helper.addAttachment(file.getName(), new File(filePath));
                }
            }
 
            javaMailSender.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }
}

