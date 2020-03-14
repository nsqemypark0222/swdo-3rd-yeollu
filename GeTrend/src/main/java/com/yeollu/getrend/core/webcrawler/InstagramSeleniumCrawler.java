package com.yeollu.getrend.core.webcrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class InstagramSeleniumCrawler {
	
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
		url = "https://www.instagram.com/soodental9/";
	}
	
	public void crawl() {
		try {
			int waitTime = 10;
			driver.get(url);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			
			Document doc = Jsoup.parse(driver.getPageSource());
			System.out.println(doc.title());
			
			Elements tables = doc.select(".ySN3v");
			Elements linksOnPage = tables.select(".v1Nh3.kIKUG._bz0w");
			
			int i = 1;
			
			for(Element page : linksOnPage) {
				System.out.println("count : " + i++);
				Element link = page.select("a").first();
				String linkHref = link.attr("href");
				System.out.println("a href : " + url + linkHref);
				Element img = page.select("img").first();
				String imgTag = img.outerHtml();
				System.out.println("img : " + imgTag);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			driver.close();
		}
	}
	

}
