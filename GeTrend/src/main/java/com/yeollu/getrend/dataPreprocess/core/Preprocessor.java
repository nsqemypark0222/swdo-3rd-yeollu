package com.yeollu.getrend.dataPreprocess.core;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.google.gson.Gson;
import com.yeollu.getrend.dataPreprocess.model.Morpheme;
import com.yeollu.getrend.dataPreprocess.model.NameEntity;

public class Preprocessor {
	private final String accessKey = "38f662c4-55a1-4b09-a3f2-405184f6a908";
	
	private final String openApiURL = "http://aiopen.etri.re.kr:8000/WiseNLU";
	
	private final String analysisCode = "ner";
	
	private static final Logger logger = LoggerFactory.getLogger(Preprocessor.class);
	
	public static String stringReplace(String str) {
        String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
        str = str.replaceAll(match, "");
        return str;
	}
	
	public ArrayList<NameEntity> run(String text) {
		Gson gson = new Gson();
		
		Map<String, Object> request = new HashMap<String, Object>();
		Map<String, Object> argument = new HashMap<String, Object>();
		
		argument.put("analysis_code", analysisCode);
		argument.put("text", text);
		
		request.put("access_key", accessKey);
		request.put("argument", argument);
		
		URL url;
		Integer responseCode = null;
		String responBodyJson = null;
		Map<String, Object> responeBody = null;
		
		Map<String, Morpheme> morphemesMap = new HashMap<String, Morpheme>();
		Map<String, NameEntity> nameEntitiesMap = new HashMap<String, NameEntity>();
		List<Morpheme> morphemes = null;
		List<NameEntity> nameEntities = null;
		
		try {
			url = new URL(openApiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.write(gson.toJson(request).getBytes("UTF-8"));
			wr.flush();
			wr.close();
			
			responseCode = con.getResponseCode();
			InputStream is = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			StringBuffer sb = new StringBuffer();
			
			String inputLine = "";
			while ((inputLine = br.readLine()) != null) {
				sb.append(inputLine);
			}
			
			responBodyJson = sb.toString();
			
			// http 요청 오류 시 처리
			if ( responseCode != 200 ) {
				// 오류 내용 출력
				System.out.println("[error] " + responBodyJson);
				return null;
			}
			
			responeBody = gson.fromJson(responBodyJson, Map.class);
			Integer result = ((Double) responeBody.get("result")).intValue();
			Map<String, Object> returnObject;
			List<Map<String, Object>> sentences;
			
			// 분석 요청 오류 시 처리
			if ( result != 0 ) {
				// 오류 내용 출력
				System.out.println("[error] " + responeBody.get("result"));
				return null;
			}
			
			returnObject = (Map<String, Object>) responeBody.get("return_object");
			sentences = (List<Map<String, Object>>) returnObject.get("sentence");
			
			
			
			for(Map<String, Object> sentence : sentences) {
				// 형태소 분석기 결과 수집 및 정렬
				List<Map<String, Object>> morphologicalAnalysisResult = (List<Map<String, Object>>) sentence.get("morp");
				for(Map<String, Object> morphemeInfo : morphologicalAnalysisResult) {
					String lemma = (String) morphemeInfo.get("lemma");
					Morpheme morpheme = morphemesMap.get(lemma);
					if ( morpheme == null ) {
						morpheme = new Morpheme(lemma, (String) morphemeInfo.get("type"), 1);
						morphemesMap.put(lemma, morpheme);
					} else {
						morpheme.setCount(morpheme.getCount() + 1);
					}
				}
				
				// 개체명 분석 결과 수집 및 정렬
				List<Map<String, Object>> nameEntityRecognitionResult = (List<Map<String, Object>>) sentence.get("NE");
//				logger.info("{}", nameEntityRecognitionResult);
				for(Map<String, Object> nameEntityInfo : nameEntityRecognitionResult) {
					String name = (String) nameEntityInfo.get("text");
					NameEntity nameEntity = nameEntitiesMap.get(name);
					if ( nameEntity == null ) {
						nameEntity = new NameEntity(name, (String) nameEntityInfo.get("type"), 1);
						nameEntitiesMap.put(name, nameEntity);
					} else {
						nameEntity.setCount(nameEntity.getCount() + 1);
					}
				}
			}
			
			if ( 0 < morphemesMap.size() ) {
				morphemes = new ArrayList<Morpheme>(morphemesMap.values());
				morphemes.sort( (morpheme1, morpheme2) -> {
					return morpheme2.getCount() - morpheme1.getCount();
				});
			}

			if ( 0 < nameEntitiesMap.size() ) {
				nameEntities = new ArrayList<NameEntity>(nameEntitiesMap.values());
				nameEntities.sort( (nameEntity1, nameEntity2) -> {
					return nameEntity2.getCount() - nameEntity1.getCount();
				});
			}
			
//			// 형태소들 중 명사들에 대해서 많이 노출된 순으로 출력 ( 최대 5개 )
//			morphemes
//				.stream()
//				.filter(morpheme -> {
//					return morpheme.getType().equals("NNG") ||
//							morpheme.getType().equals("NNP") ||
//							morpheme.getType().equals("NNB");
//				})
//				.limit(100)
//				.forEach(morpheme -> {
//					System.out.println("[명사] " + morpheme.getText() + " ("+morpheme.getCount()+")" );
//				});
//			
//			// 형태소들 중 동사들에 대해서 많이 노출된 순으로 출력 ( 최대 5개 )
//			System.out.println("");
//			morphemes
//				.stream()
//				.filter(morpheme -> {
//					return morpheme.getType().equals("VV");
//				})
//				.limit(5)
//				.forEach(morpheme -> {
//					System.out.println("[동사] " + morpheme.getText() + " ("+morpheme.getCount()+")" );
//				});
//			
//			// 인식된 개채명들 많이 노출된 순으로 출력 ( 최대 5개 )
//			System.out.println("");
//			nameEntities
//				.stream()
//				.limit(100)
//				.forEach(nameEntity -> {
//					System.out.println("[개체명] " + nameEntity.getText() + " ("+nameEntity.getCount()+") " + nameEntity.getType() );
//				});
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return (ArrayList<NameEntity>) nameEntities;
	}
}
