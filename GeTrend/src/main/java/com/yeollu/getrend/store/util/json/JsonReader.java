package com.yeollu.getrend.store.util.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @Class 	: JsonReader.java
 * @Package	: com.yeollu.getrend.store.util.json
 * @Project : GeTrend
 * @Author	: 조은채, 박민열
 * @Since	: 2020. 3. 26.
 * @Version	: 1.0
 * @Desc	:
 */
public class JsonReader {
	
	/**
	 * @Method	: readAll
	 * @Return	: String
	 * @Author	: 조은채, 박민열
	 * @Since	: 2020. 3. 26.
	 * @Version	: 1.0
	 * @Desc	: Reader로부터 문자를 읽어 문자열로 만들어 반환한다.
	 * @param rd
	 * @throws IOException
	 */
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	    	sb.append((char) cp);
	    }
	    return sb.toString();
	}
	
	/**
	 * @Method	: readJsonFromUrl
	 * @Return	: JSONObject
	 * @Author	: 조은채, 박민열
	 * @Since	: 2020. 3. 26.
	 * @Version	: 1.0
	 * @Desc	: URL로부터 JSON을 추출해 반환한다.
	 * @param url
	 * @throws IOException
	 * @throws JSONException
	 */
	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
	    try {
	    	BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	    	String jsonText = readAll(rd);
	    	JSONObject json = new JSONObject(jsonText);
	    	return json;
	    } finally {
	    	is.close();
	    }
	}
}
