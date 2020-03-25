package com.yeollu.getrend.store.util.preprocess.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringPreprocessor {
	
	private static final Logger logger = LoggerFactory.getLogger(StringPreprocessor.class);
	
	public static String stringReplace(String str) {
        String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
        str = str.replaceAll(match, "");
        return str;
	}
}
