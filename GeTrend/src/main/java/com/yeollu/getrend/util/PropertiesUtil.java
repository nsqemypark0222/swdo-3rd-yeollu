package com.yeollu.getrend.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Class 	: PropertiesUtil.java
 * @Package	: com.yeollu.getrend.util
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 3. 29.
 * @Version	: 1.0
 * @Desc	:
 */
public class PropertiesUtil {
	
	/**
	 * Fields
	 */
	private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
	private static HashMap<String, Properties> propses;
	
	/**
	 * @Method	: get
	 * @Return	: String
	 * @Author	: 박민열
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: propses에서 prop의 값을 찾아 property를 반환한다.
	 * @param prop
	 * @param key
	 */
	public static String get(String prop, String key) {
		if(load(prop) == true) {
			return propses.get(prop).getProperty(key);
		} else {
			return "";
		}
	}
	
	/**
	 * @Method	: load
	 * @Return	: boolean
	 * @Author	: 박민열
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: (매개변수로 넘겨받은 prop).properties 파일을 읽어 들여 propses에 저장한다.
	 * @param prop
	 * @return
	 */
	private static boolean load(String prop) {
		if(propses == null) {
			init();
		}
		if(propses.containsKey(prop)) {
			return true;
		} else {
			try {
				StringBuilder path = new StringBuilder();
				path.append("properties/").append(prop).append(".properties");
//				logger.info("{}", path);
				InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path.toString());
				Properties props = new Properties();
				props.load(is);
				
				propses.put(prop, props);
				
				is.close();
				
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}
	
	/**
	 * @Method	: init
	 * @Return	: void
	 * @Author	: 박민열
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: propses를 초기화한다.
	 */
	private static void init() {
		propses = new HashMap<String, Properties>();
	}
}
