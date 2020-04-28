package com.yeollu.getrend.crawler;


import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yeollu.getrend.store.vo.InstaImageVO;

public class CrawlerExecutor implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(CrawlerExecutor.class);
	
	private InstagramLocationCrawler crawler;
	
	private String store_no;
	
	private String location_id;
	
	private boolean isExisted = false;
	
	private boolean isRequiredUpdate = false;
	
	private ArrayList<InstaImageVO> instaImageList;
	
	private volatile boolean done = false;

	public CrawlerExecutor() {
		crawler = new InstagramLocationCrawler();
	}
	
	@Override
	public void run() {
		if(isExisted == false || isRequiredUpdate == true) {
			logger.info("크롤링 : {}", store_no);
			instaImageList = crawler.location_post(store_no, location_id);
			done = true;
			synchronized (this) {
				this.notifyAll();
			}
		} else if(isExisted == true && isRequiredUpdate == false) {
			logger.info("업데이트 할 필요가 없음 : {}", store_no);
		} else {
			// ignore
		}
	}
	
	public void setStore_no(String store_no) {
		this.store_no = store_no;
	}
	
	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}
	
	public void setIsRequiredUpdate(boolean isRequiredUpdate) {
		this.isRequiredUpdate = isRequiredUpdate;
	}
	
	public void setIsExisted(boolean isExisted) {
		this.isExisted = isExisted;
	}
	
	public boolean getIsRequiredUpdate() {
		return isRequiredUpdate;
	}

	public String getStore_no() {
		return store_no;
	}
	
	public boolean getIsExisted() {
		return isExisted;
	}


	public ArrayList<InstaImageVO> getInstaImageList() {
		if(!done) {
			synchronized (this) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		if(instaImageList != null) {
			logger.info("{}", instaImageList);
			return instaImageList;
		} else {
			return null;
		}
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
