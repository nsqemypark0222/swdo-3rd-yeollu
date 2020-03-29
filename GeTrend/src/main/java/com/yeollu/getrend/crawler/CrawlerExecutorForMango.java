package com.yeollu.getrend.crawler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yeollu.getrend.store.vo.InstaImageVO;
import com.yeollu.getrend.store.vo.MangoStoreVO;
import com.yeollu.getrend.store.vo.StoreVO;

public class CrawlerExecutorForMango implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(CrawlerExecutorForMango.class);
	
	private mango_store_info2 crawler;
	
	private StoreVO store;
	
	private MangoStoreVO mangoStore;
	
	private volatile boolean done = false;

	public CrawlerExecutorForMango() {
		crawler = new mango_store_info2();
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
}
