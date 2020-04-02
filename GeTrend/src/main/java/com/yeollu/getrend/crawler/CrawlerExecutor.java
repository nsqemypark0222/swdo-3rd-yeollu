package com.yeollu.getrend.crawler;


import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yeollu.getrend.store.vo.InstaImageVO;

public class CrawlerExecutor implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(CrawlerExecutor.class);
	
	private instagram_Selenium_location_post3 crawler;
	
	private String location;
	
	private InstaImageVO instaImage;
	
	private volatile boolean done = false;

	public CrawlerExecutor() {
		crawler = new instagram_Selenium_location_post3();
	}
	
	@Override
	public void run() {
		instaImage = crawler.location_post(location);
		done = true;
		synchronized (this) {
			this.notifyAll();
		}
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public InstaImageVO getInstaImage() {
		if(!done) {
			synchronized (this) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		logger.info("{}", instaImage);
		return instaImage;
	}
	
	public static void killChromeDriver() {
		try {
			Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe /t");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
