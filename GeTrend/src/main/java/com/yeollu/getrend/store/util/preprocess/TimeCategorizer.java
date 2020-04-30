package com.yeollu.getrend.store.util.preprocess;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Class 	: TimeCategorizer.java
 * @Package	: com.yeollu.getrend.store.util.preprocess
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 3. 29.
 * @Version	: 1.0
 * @Desc	: 망고플레이트 웹 크롤링 결과를 데이터 베이스에 저장하기 위한 전처리를 수행한다.
 */
public class TimeCategorizer {
	
	/**
	 * Fields
	 */
	private static final Logger logger = LoggerFactory.getLogger(TimeCategorizer.class);
	private HashMap<String, String> resultMap;
	
	/**
	 * Constructor
	 */
	public TimeCategorizer() {
		resultMap = new HashMap<String, String>();
		resultMap.put("시작시간", "");
		resultMap.put("종료시간", "");
	}
	
	/**
	 * @Method	: categorize
	 * @Return	: void
	 * @Author	: 박민열
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: 시간을 매개변수로 넘겨받아 시작시간과 종료시간 2가지로 추출해 resultMap에 저장한다.
	 * @param str
	 */
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
	
	/**
	 * @Method	: getResultMap
	 * @Return	: HashMap<String,String>
	 * @Author	: 박민열
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: Getter
	 */
	public HashMap<String, String> getResultMap() {
		return resultMap;
	}

}
