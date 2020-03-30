package com.yeollu.getrend.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
	
	private static HashMap<String, Properties> propses;
	
	public static String get(String prop, String key) {
		if(load(prop) == true) {
			return propses.get(prop).getProperty(key);
		} else {
			return "";
		}
	}
	
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
	
	private static void init() {
		propses = new HashMap<String, Properties>();
	}
}
