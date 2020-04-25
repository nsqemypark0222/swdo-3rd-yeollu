package com.yeollu.getrend.store.util.preprocess;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeCategorizer {
	
	private static final Logger logger = LoggerFactory.getLogger(TimeCategorizer.class);
	
	private HashMap<String, String> resultMap;
	
	public TimeCategorizer() {
		resultMap = new HashMap<String, String>();
		resultMap.put("시작시간", "");
		resultMap.put("종료시간", "");
	}
	
	public void categorize(String str) {
		if(str == null || str.length() == 0) {
			return;
		}
		str = StringPreprocessor.removeSpace(str);
		if(StringPreprocessor.isHangul(str.substring(0, 1))) {
			if(StringPreprocessor.isDigit(str.substring(2, 3))) {
				str = str.substring(2);
				logger.info("{}", str);
				resultMap.put("시작시간", str.substring(0, 5));
				resultMap.put("종료시간", str.substring(6, 11));
			} else {
				str = str.substring(4);
				logger.info("{}", str);
				resultMap.put("시작시간", str.substring(0, 5));
				resultMap.put("종료시간", str.substring(6, 11));
			}
		} else if(StringPreprocessor.isDigit(str.substring(0, 1))){
			resultMap.put("시작시간", str.substring(0, 5));
			resultMap.put("종료시간", str.substring(6, 11));
		}
		
	}
	
	public HashMap<String, String> getResultMap() {
		return resultMap;
	}

}
