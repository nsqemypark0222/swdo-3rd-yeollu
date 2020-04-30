package com.yeollu.getrend.crawler;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yeollu.getrend.mango.vo.MangoStoreVO;
import com.yeollu.getrend.store.vo.StoreVO;

/**
 * @Class 	: CrawlerExecutorForMango.java
 * @Package	: com.yeollu.getrend.crawler
 * @Project : GeTrend
 * @Author	: 조은채, 박민열
 * @Since	: 2020. 3. 29.
 * @Version	: 1.0
 * @Desc	: 멀티스레드를 이용하여 크롤러를 실행시킨다.
 */
public class CrawlerExecutorForMango implements Runnable {
	
	/**
	 * Fields
	 */
	private static final Logger logger = LoggerFactory.getLogger(CrawlerExecutorForMango.class);
	private MangoPlateCrawler crawler;
	private StoreVO store;
	private MangoStoreVO mangoStore;
	private volatile boolean done = false;

	/**
	 * Constructor
	 */
	public CrawlerExecutorForMango() {
		crawler = new MangoPlateCrawler();
	}
	
	/**
	 * Overriding
	 * @Method	: run
	 * @Return	: void
	 * @Author	: 조은채, 박민열
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: 크롤러를 실행한다.
	 */
	@Override
	public void run() {
		mangoStore = crawler.crawl(store);
		done = true;
		synchronized (this) {
			this.notifyAll();
		}
	}
	
	/**
	 * @Method	: setStore
	 * @Return	: void
	 * @Author	: 조은채, 박민열
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: Setter
	 * @param store
	 */
	public void setStore(StoreVO store) {
		this.store = store;
	}
	
	/**
	 * @Method	: getMangoStore
	 * @Return	: MangoStoreVO
	 * @Author	: 조은채, 박민열
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: Getter
	 */
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
	
	/**
	 * @Method	: killChromeDriver
	 * @Return	: void
	 * @Author	: 조은채, 박민열
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: 크롤링에 사용된 크롬 드라이버를 종료시킨다.
	 */
	public static void killChromeDriver() {
		try {
			Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe /t");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
