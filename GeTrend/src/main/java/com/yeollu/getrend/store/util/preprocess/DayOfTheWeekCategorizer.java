package com.yeollu.getrend.store.util.preprocess;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Class 	: DayOfTheWeekCategorizer.java
 * @Package	: com.yeollu.getrend.store.util.preprocess
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 3. 29.
 * @Version	: 1.0
 * @Desc	: 망고플레이트 웹 크롤링 결과를 데이터 베이스에 저장하기 위한 전처리를 수행한다.
 */
public class DayOfTheWeekCategorizer {
	
	/**
	 * Fields
	 */
	private static final Logger logger = LoggerFactory.getLogger(DayOfTheWeekCategorizer.class);
	private HashMap<String, String> resultMap;
	
	/**
	 * Constructor
	 */
	public DayOfTheWeekCategorizer() {
		resultMap = new HashMap<String, String>();
		resultMap.put("일", "0");
		resultMap.put("월", "0");
		resultMap.put("화", "0");
		resultMap.put("수", "0");
		resultMap.put("목", "0");
		resultMap.put("금", "0");
		resultMap.put("토", "0");
	}
	
	/**
	 * @Method	: categorize
	 * @Return	: void
	 * @Author	: 박민열
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: 요일을 매개변수로 넘겨받아 일,월,화,수,목,금,토 7가지로 추출해 resultMap에 저장한다.
	 * @param str
	 */
	public void categorize(String str) {
		if(str == null || str.length() == 0) {
			resultMap.put("일", "");
			resultMap.put("월", "");
			resultMap.put("화", "");
			resultMap.put("수", "");
			resultMap.put("목", "");
			resultMap.put("금", "");
			resultMap.put("토", "");
			return;
		}
		logger.info("str : {}", str);
		if(StringPreprocessor.isHangul(str.substring(0, 1))) {
			logger.info("str.charAt(0) : {}", str.substring(0, 1));
			if(str.substring(1, 2).equals(":")) {
				switch (str.substring(0, 1)) {
				case "일":
					resultMap.put("일", "1");
					break;
				case "월":
					resultMap.put("월", "1");
					break;
				case "화":
					resultMap.put("화", "1");
					break;
				case "수":
					resultMap.put("수", "1");
					break;
				case "목":
					resultMap.put("목", "1");
					break;
				case "금":
					resultMap.put("금", "1");
					break;
				case "토":
					resultMap.put("토", "1");
					break;
				}
			} else if(str.charAt(1) == '-') {
				logger.info("str.charAt(2) : {}", str.substring(2, 3));
				switch (str.substring(0, 1)) {
				case "일":
					switch (str.substring(2, 3)) {
						case "월":
							resultMap.put("일", "1");
							resultMap.put("월", "1");
							break;
						case "화":
							resultMap.put("일", "1");
							resultMap.put("월", "1");
							resultMap.put("화", "1");
							break;
						case "수":
							resultMap.put("일", "1");
							resultMap.put("월", "1");
							resultMap.put("화", "1");
							resultMap.put("수", "1");
							break;
						case "목":
							resultMap.put("일", "1");
							resultMap.put("월", "1");
							resultMap.put("화", "1");
							resultMap.put("수", "1");
							resultMap.put("목", "1");
							break;
						case "금":
							resultMap.put("일", "1");
							resultMap.put("월", "1");
							resultMap.put("화", "1");
							resultMap.put("수", "1");
							resultMap.put("목", "1");
							resultMap.put("금", "1");
							break;
						case "토":
							resultMap.put("일", "1");
							resultMap.put("월", "1");
							resultMap.put("화", "1");
							resultMap.put("수", "1");
							resultMap.put("목", "1");
							resultMap.put("금", "1");
							resultMap.put("토", "1");
							break;
					}
					break;
				case "월":
					switch (str.substring(2, 3)) {
						case "화":
							resultMap.put("월", "1");
							resultMap.put("화", "1");
							break;
						case "수":
							resultMap.put("월", "1");
							resultMap.put("화", "1");
							resultMap.put("수", "1");
							break;
						case "목":
							resultMap.put("월", "1");
							resultMap.put("화", "1");
							resultMap.put("수", "1");
							resultMap.put("목", "1");
							break;
						case "금":
							resultMap.put("월", "1");
							resultMap.put("화", "1");
							resultMap.put("수", "1");
							resultMap.put("목", "1");
							resultMap.put("금", "1");
							break;
						case "토":
							resultMap.put("월", "1");
							resultMap.put("화", "1");
							resultMap.put("수", "1");
							resultMap.put("목", "1");
							resultMap.put("금", "1");
							resultMap.put("토", "1");
							break;
						case "일":
							resultMap.put("월", "1");
							resultMap.put("화", "1");
							resultMap.put("수", "1");
							resultMap.put("목", "1");
							resultMap.put("금", "1");
							resultMap.put("토", "1");
							resultMap.put("월", "1");
							break;
					}
					break;
				case "화":
					switch (str.substring(2, 3)) {
						case "수":
							resultMap.put("화", "1");
							resultMap.put("수", "1");
							break;
						case "목":
							resultMap.put("화", "1");
							resultMap.put("수", "1");
							resultMap.put("목", "1");
							break;
						case "금":
							resultMap.put("화", "1");
							resultMap.put("수", "1");
							resultMap.put("목", "1");
							resultMap.put("금", "1");
							break;
						case "토":
							resultMap.put("화", "1");
							resultMap.put("수", "1");
							resultMap.put("목", "1");
							resultMap.put("금", "1");
							resultMap.put("토", "1");
							break;
						case "일":
							resultMap.put("화", "1");
							resultMap.put("수", "1");
							resultMap.put("목", "1");
							resultMap.put("금", "1");
							resultMap.put("토", "1");
							resultMap.put("일", "1");
							break;
						case "월":
							resultMap.put("화", "1");
							resultMap.put("수", "1");
							resultMap.put("목", "1");
							resultMap.put("금", "1");
							resultMap.put("토", "1");
							resultMap.put("일", "1");
							resultMap.put("월", "1");
							break;
					}
					break;
				case "수":
					switch (str.substring(2, 3)) {
						case "목":
							resultMap.put("수", "1");
							resultMap.put("목", "1");
							break;
						case "금":
							resultMap.put("수", "1");
							resultMap.put("목", "1");
							resultMap.put("금", "1");
							break;
						case "토":
							resultMap.put("수", "1");
							resultMap.put("목", "1");
							resultMap.put("금", "1");
							resultMap.put("토", "1");
							break;
						case "일":
							resultMap.put("수", "1");
							resultMap.put("목", "1");
							resultMap.put("금", "1");
							resultMap.put("토", "1");
							resultMap.put("일", "1");
							break;
						case "월":
							resultMap.put("수", "1");
							resultMap.put("목", "1");
							resultMap.put("금", "1");
							resultMap.put("토", "1");
							resultMap.put("일", "1");
							resultMap.put("월", "1");
							break;
						case "화":
							resultMap.put("수", "1");
							resultMap.put("목", "1");
							resultMap.put("금", "1");
							resultMap.put("토", "1");
							resultMap.put("일", "1");
							resultMap.put("월", "1");
							resultMap.put("화", "1");
							break;
					}
					break;
				case "목":
					switch (str.substring(2, 3)) {
						case "금":
							resultMap.put("목", "1");
							resultMap.put("금", "1");
							break;
						case "토":
							resultMap.put("목", "1");
							resultMap.put("금", "1");
							resultMap.put("토", "1");
							break;
						case "일":
							resultMap.put("목", "1");
							resultMap.put("금", "1");
							resultMap.put("토", "1");
							resultMap.put("일", "1");
							break;
						case "월":
							resultMap.put("목", "1");
							resultMap.put("금", "1");
							resultMap.put("토", "1");
							resultMap.put("일", "1");
							resultMap.put("월", "1");
							break;
						case "화":
							resultMap.put("목", "1");
							resultMap.put("금", "1");
							resultMap.put("토", "1");
							resultMap.put("일", "1");
							resultMap.put("월", "1");
							resultMap.put("화", "1");
							break;
						case "수":
							resultMap.put("목", "1");
							resultMap.put("금", "1");
							resultMap.put("토", "1");
							resultMap.put("일", "1");
							resultMap.put("월", "1");
							resultMap.put("화", "1");
							resultMap.put("수", "1");
							break;
					}
					break;
				case "금":
					switch (str.substring(2, 3)) {
						case "토":
							resultMap.put("금", "1");
							resultMap.put("토", "1");
							break;
						case "일":
							resultMap.put("금", "1");
							resultMap.put("토", "1");
							resultMap.put("일", "1");
							break;
						case "월":
							resultMap.put("금", "1");
							resultMap.put("토", "1");
							resultMap.put("일", "1");
							resultMap.put("월", "1");
							break;
						case "화":
							resultMap.put("금", "1");
							resultMap.put("토", "1");
							resultMap.put("일", "1");
							resultMap.put("월", "1");
							resultMap.put("화", "1");
							break;
						case "수":
							resultMap.put("금", "1");
							resultMap.put("토", "1");
							resultMap.put("일", "1");
							resultMap.put("월", "1");
							resultMap.put("화", "1");
							resultMap.put("수", "1");
							break;
						case "목":
							resultMap.put("금", "1");
							resultMap.put("토", "1");
							resultMap.put("일", "1");
							resultMap.put("월", "1");
							resultMap.put("화", "1");
							resultMap.put("수", "1");
							resultMap.put("목", "1");
							break;
					}
					break;
				case "토":
					switch (str.substring(2, 3)) {
						case "일":
							resultMap.put("토", "1");
							resultMap.put("일", "1");
							break;
						case "월":
							resultMap.put("토", "1");
							resultMap.put("일", "1");
							resultMap.put("월", "1");
							break;
						case "화":
							resultMap.put("토", "1");
							resultMap.put("일", "1");
							resultMap.put("월", "1");
							resultMap.put("화", "1");
							break;
						case "수":
							resultMap.put("토", "1");
							resultMap.put("일", "1");
							resultMap.put("월", "1");
							resultMap.put("화", "1");
							resultMap.put("수", "1");
							break;
						case "목":
							resultMap.put("토", "1");
							resultMap.put("일", "1");
							resultMap.put("월", "1");
							resultMap.put("화", "1");
							resultMap.put("수", "1");
							resultMap.put("목", "1");
							break;
						case "금":
							resultMap.put("토", "1");
							resultMap.put("일", "1");
							resultMap.put("월", "1");
							resultMap.put("화", "1");
							resultMap.put("수", "1");
							resultMap.put("목", "1");
							resultMap.put("금", "1");
							break;
					}
					break;
				}
			}
		} else if(StringPreprocessor.isDigit(str.substring(0, 1))) {
			resultMap.put("일", "1");
			resultMap.put("월", "1");
			resultMap.put("화", "1");
			resultMap.put("수", "1");
			resultMap.put("목", "1");
			resultMap.put("금", "1");
			resultMap.put("토", "1");
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
