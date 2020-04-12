package com.yeollu.getrend.store.util.preprocess.core;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringPreprocessor {
	
	private static final Logger logger = LoggerFactory.getLogger(StringPreprocessor.class);
	
	public static String stringReplace(String str) {
        String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
        str = str.replaceAll(match, "");
        return str;
	}
	
	public static boolean isHangul(String str) {
		if(Pattern.matches("^[ㄱ-ㅎ가-힣]*$", str)) {
			return true;
		}
		return false;
	}
	
	public static boolean isDigit(String str) {
		if(Character.isDigit(str.charAt(0))) {
			return true;
		}
		return false;
	}
	
	public static boolean isTime(String str) {
		String match = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
		if(Pattern.matches(match, str)) {
			return true;
		}
		return false;
	}
	
	public static String removeSpace(String str) {
		return str.replaceAll(" ", "");
	}
}
