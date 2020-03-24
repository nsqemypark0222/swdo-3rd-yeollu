package com.yeollu.getrend.dataPreprocess.core;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yeollu.getrend.dataPreprocess.model.NameEntity;

public class TimeWordPreprocessor {
	
	private static final Logger logger = LoggerFactory.getLogger(TimeWordPreprocessor.class);

	public void run(ArrayList<NameEntity> entities) {
		if(entities != null) {
			entities
			.stream()
			.limit(100)
			.forEach(nameEntity -> {
				switch (nameEntity.getType()) {
				case "TI_DURATION":
					String ti_duration = nameEntity.getText().replace(" ", "");
//					logger.info("TI_기간으로 분류 : {}", ti_duration);
					if(ti_duration.startsWith("점심")) {
						ti_duration = ti_duration.substring(2);
						if(ti_duration.split("~").length  > 1) {
							logger.info("점심 영업시작시간 : {}", ti_duration.split("~")[0]);
							logger.info("점심 영업종료시간 : {}", ti_duration.split("~")[1]);
						}
					} else if(ti_duration.startsWith("저녁")) {
						ti_duration = ti_duration.substring(2);
						if(ti_duration.split("~").length  > 1) {
							logger.info("저녁 영업시작시간 : {}", ti_duration.split("~")[0]);
							logger.info("저녁 영업종료시간 : {}", ti_duration.split("~")[1]);	
						}
					}
					break;
				case "TI_HOUR":
					String ti_hour = nameEntity.getText().replace(" ", "");
//					logger.info("TI_시간으로 분류 before : {}", ti_hour);
					if(ti_hour.contains(":")) {
						if(ti_hour.startsWith("0")) {
							ti_hour = ti_hour.substring(0);
						}
						logger.info("TI_시간으로 분류 after : {}", ti_hour);
					}
					break;
//				case "TI_MINUTE":
//					logger.info("TI_분으로 분류 : {}", nameEntity.getText());
//					break;
				case "TI_OTHERS":
					String ti_others = nameEntity.getText().replace(" ", "");
//					logger.info("TI_기타로 분류 before : {}", ti_others);
					if(ti_others.contains(":")) {
						if(ti_others.startsWith("0")) {
							ti_others = ti_others.substring(0);
						}
						logger.info("TI_기타로 분류 after : {}", ti_others);
					}
					break;
				case "DT_DURATION":
					String dt_duration = Preprocessor.stringReplace(nameEntity.getText().replace(" ", ""));
					logger.info("DT_기간로 분류 : {}", dt_duration);
					break;
				case "DT_DAY":
					String dt_day = Preprocessor.stringReplace(nameEntity.getText().replace(" ", ""));
					logger.info("DT_일자로 분류 : {}", dt_day);
					break;
				case "DT_OTHERS":
					String dt_others = Preprocessor.stringReplace(nameEntity.getText().replace(" ", ""));
					if(dt_others.matches((".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*"))) {
						logger.info("DT_기타로 분류 : {}", dt_others);
					}
					break;
				}
			});
		}
		
		logger.info("");
	}
}
