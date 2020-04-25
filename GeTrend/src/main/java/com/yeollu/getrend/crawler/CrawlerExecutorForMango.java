package com.yeollu.getrend.crawler;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yeollu.getrend.mango.vo.MangoStoreVO;
import com.yeollu.getrend.store.vo.StoreVO;

public class CrawlerExecutorForMango implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(CrawlerExecutorForMango.class);
	
	private MangoPlateCrawler crawler;
	
	private StoreVO store;
	
	private MangoStoreVO mangoStore;
	
	private volatile boolean done = false;

	public CrawlerExecutorForMango() {
		crawler = new MangoPlateCrawler();
	}
	
	@Override
	public void run() {
		mangoStore = crawler.crawl(store);
		done = true;
		synchronized (this) {
			this.notifyAll();
		}
	}
	
	public void setStore(StoreVO store) {
		this.store = store;
	}
	
	public MangoStoreVO getMangoStore() {
		if(!done) {
			synchronized (this) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		logger.info("{}", mangoStore);
		return mangoStore;
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
