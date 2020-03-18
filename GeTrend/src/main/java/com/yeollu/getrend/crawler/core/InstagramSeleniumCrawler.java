package com.yeollu.getrend.crawler.core;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yeollu.getrend.store.vo.IStoreInfoVO;

public class InstagramSeleniumCrawler {
	
	private static final Logger logger = LoggerFactory.getLogger(InstagramSeleniumCrawler.class);
	
	// WebDriver
	private WebDriver driver;
	private WebElement element;
	
	// Properties
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "chromedriver.exe";
	
	private String url = null;
	
	public InstagramSeleniumCrawler() {
		// System Property SetUp
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		driver = new ChromeDriver();
	}
	
	public void crawl() {
		try {
			
			url = "https://www.instagram.com/precikaffe/";
			driver.get(url);
			
			Document doc = Jsoup.parse(driver.getPageSource());
			logger.info("{}", doc.title());
			
			Elements profileContainer = doc.select(".-vDIg");
			
			Element span = profileContainer.select("span").first();
			String spanText = span.ownText();
			logger.info("{}", spanText);
			
			Elements postListContainer = doc.select(".ySN3v");
			Elements postContainer = postListContainer.select(".v1Nh3.kIKUG._bz0w");
			
			ArrayList<IStoreInfoVO> iStoreInfoList = new ArrayList<IStoreInfoVO>();
			
			for(Element post : postContainer) {
//				Element linkOfPost = post.select("a").first();
//				String linkHref = linkOfPost.attr("href");
//				iStoreInfoList.add("https://www.instagram.com/" + linkHref);
//				logger.info("{}", linkHref);
				
				Element linkOfImg = post.select("img").first();
				String img = linkOfImg.attr("src");
				IStoreInfoVO iStoreInfo = new IStoreInfoVO();
//				iStoreInfo.setInsta_id(insta_id);
				iStoreInfo.setInsta_id("precikaffe");
				iStoreInfo.setInfo_img(img);
				iStoreInfoList.add(iStoreInfo);
				logger.info("{}", img);
			}
			logger.info("{}", iStoreInfoList.size());
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			driver.close();
		}
	}
	

}
