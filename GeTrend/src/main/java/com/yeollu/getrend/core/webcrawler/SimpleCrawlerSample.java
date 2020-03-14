package com.yeollu.getrend.core.webcrawler;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SimpleCrawlerSample {
	
	private WebDriver driver;
	private WebElement element;
	
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "chromedriver.exe";

	public void run() throws IOException {
		
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		driver = new ChromeDriver();
		String url = "https://www.instagram.com/soodental9/";
		
		driver.get(url);
		
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
		
		
	}
}
