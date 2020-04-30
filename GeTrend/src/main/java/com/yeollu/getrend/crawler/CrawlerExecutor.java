package com.yeollu.getrend.crawler;


import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yeollu.getrend.store.vo.InstaImageVO;


/**
 * @Class 	: CrawlerExecutor.java
 * @Package	: com.yeollu.getrend.crawler
 * @Project : GeTrend
 * @Author	: 조은채, 박민열
 * @Since	: 2020. 3. 29.
 * @Version	: 1.0
 * @Desc	: 멀티스레드를 이용하여 크롤러를 실행시킨다.
 */
public class CrawlerExecutor implements Runnable {
	
	/**
	 * Fields
	 */
	private static final Logger logger = LoggerFactory.getLogger(CrawlerExecutor.class);
	private InstagramLocationCrawler crawler;
	private String store_no;
	private String location_id;
	private boolean isExisted = false;
	private boolean isRequiredUpdate = false;
	private ArrayList<InstaImageVO> instaImageList;
	private volatile boolean done = false;
	
	/**
	 * Constructor
	 */
	public CrawlerExecutor() {
		crawler = new InstagramLocationCrawler();
	}
	
	/**
	 * Overriding
	 * @Method	: run
	 * @Return	: void
	 * @Author	: 조은채, 박민열
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: insta_images 테이블에 이미지 URL이 존재하며 업데이트가 필요할 경우 크롤러를 실행한다.
	 */
	@Override
	public void run() {
		if(isExisted == false || isRequiredUpdate == true) {
			logger.info("크롤링 : {}", store_no);
			instaImageList = crawler.crawl(store_no, location_id);
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
	
	/**
	 * @Method	: setStore_no
	 * @Return	: void
	 * @Author	: 조은채, 박민열
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: Setter
	 * @param store_no
	 */
	public void setStore_no(String store_no) {
		this.store_no = store_no;
	}
	
	/**
	 * @Method	: setLocation_id
	 * @Return	: void
	 * @Author	: 조은채, 박민열
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: Setter
	 * @param location_id
	 */
	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}
	
	/**
	 * @Method	: setIsRequiredUpdate
	 * @Return	: void
	 * @Author	: 조은채, 박민열
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: Setter
	 * @param isRequiredUpdate
	 */
	public void setIsRequiredUpdate(boolean isRequiredUpdate) {
		this.isRequiredUpdate = isRequiredUpdate;
	}
	
	/**
	 * @Method	: setIsExisted
	 * @Return	: void
	 * @Author	: 조은채, 박민열
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: Setter
	 * @param isExisted
	 */
	public void setIsExisted(boolean isExisted) {
		this.isExisted = isExisted;
	}
	
	/**
	 * @Method	: getIsRequiredUpdate
	 * @Return	: boolean
	 * @Author	: 조은채, 박민열
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: Getter
	 */
	public boolean getIsRequiredUpdate() {
		return isRequiredUpdate;
	}

	/**
	 * @Method	: getStore_no
	 * @Return	: String
	 * @Author	: 조은채, 박민열
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: Getter
	 */
	public String getStore_no() {
		return store_no;
	}
	
	/**
	 * @Method	: getIsExisted
	 * @Return	: boolean
	 * @Author	: 조은채, 박민열
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: Getter
	 */
	public boolean getIsExisted() {
		return isExisted;
	}


	/**
	 * @Method	: getInstaImageList
	 * @Return	: ArrayList<InstaImageVO>
	 * @Author	: 조은채, 박민열
	 * @Since	: 2020. 3. 29.
	 * @Version	: 1.0
	 * @Desc	: Getter, 크롤링 된 이미지들을 반환한다. 이미지가 존재하지 않을 경우 null을 반환한다.
	 */
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
