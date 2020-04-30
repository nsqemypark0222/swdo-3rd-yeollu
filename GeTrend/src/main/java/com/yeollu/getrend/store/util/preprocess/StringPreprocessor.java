package com.yeollu.getrend.store.util.preprocess;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Class 	: StringPreprocessor.java
 * @Package	: com.yeollu.getrend.store.util.preprocess
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 3. 29.
 * @Version	: 1.0
 * @Desc	: 각종 문자열을 처리하기 쉽도록 변환해준다.
 */
public class StringPreprocessor {
	
	/**
	 * Fields
	 */
	private static final Logger logger = LoggerFactory.getLogger(StringPreprocessor.class);
	
	/**
	 * @Method	: stringReplace
	 * @Return	: String
	 * @Author	: 박민열
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: 문자열을 입력받아 특수문자를 제거해 반환한다.
	 * @param str
	 */
	public static String stringReplace(String str) {
        String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
        str = str.replaceAll(match, "");
        return str;
	}
	
	/**
	 * @Method	: isHangul
	 * @Return	: boolean
	 * @Author	: 박민열
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: 문자열을 입력받아 한글이면 true, 한글이 아니면 false를 반환한다.
	 * @param str
	 */
	public static boolean isHangul(String str) {
		if(Pattern.matches("^[ㄱ-ㅎ가-힣]*$", str)) {
			return true;
		}
		return false;
	}
	
	/**
	 * @Method	: isDigit
	 * @Return	: boolean
	 * @Author	: 박민열
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: 문자열을 입력받아 숫자이면 true, 숫자가 아니면 false를 반환한다.
	 * @param str
	 */
	public static boolean isDigit(String str) {
		if(Character.isDigit(str.charAt(0))) {
			return true;
		}
		return false;
	}
	
	/**
	 * @Method	: isTime
	 * @Return	: boolean
	 * @Author	: 박민열
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: 문자열을 입력받아 정해진 형식(00:00)의 시간이면 true, 아니면 false를 반환한다.
	 * @param str
	 */
	public static boolean isTime(String str) {
		String match = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
		if(Pattern.matches(match, str)) {
			return true;
		}
		return false;
	}
	
	/**
	 * @Method	: removeSpace
	 * @Return	: String
	 * @Author	: 박민열
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: 문자열을 입력받아 공백(" ")을 제거해 반환한다.
	 * @param str
	 */
	public static String removeSpace(String str) {
		return str.replaceAll(" ", "");
	}
}
