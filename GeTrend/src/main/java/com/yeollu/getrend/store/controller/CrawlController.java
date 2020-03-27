package com.yeollu.getrend.store.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.yeollu.getrend.crawler.mango_store_info;

@Controller
@RequestMapping(value="/crawl")
public class CrawlController {
	
	private static final Logger logger = LoggerFactory.getLogger(CrawlController.class);


	@RequestMapping(value = "/mango_store_info", method = RequestMethod.POST)
	public String mango_store_info(String store_name) {
		logger.info("망플 가게 정보");
		long startTime = System.currentTimeMillis();
		mango_store_info mango = new mango_store_info();
		mango.crawl(store_name);
		long endTime = System.currentTimeMillis();
		long diff = (endTime - startTime) / 1000;
		logger.info("걸린 시간 : {}", diff);
		return "crawl_test";
	}
	
}
