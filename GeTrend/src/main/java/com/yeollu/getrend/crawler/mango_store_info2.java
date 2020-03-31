package com.yeollu.getrend.crawler;

import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yeollu.getrend.store.vo.MangoStoreVO;
import com.yeollu.getrend.store.vo.StoreVO;
import com.yeollu.getrend.util.PropertiesUtil;

public class mango_store_info2 {
	
	private static final Logger logger = LoggerFactory.getLogger(mango_store_info2.class);
	
	private static ChromeOptions options;
	
	//WebDriver
	private WebDriver driver;
	
	private WebDriverWait wait;
	
	static {
//		System.setProperty("webdriver.chrome.driver", "C:/stsinstall/sts-4.5.0.RELEASE/chromedriver.exe");
//		System.setProperty("webdriver.chrome.driver", "C:/sts/sts-4.5.0.RELEASE/chromedriver.exe");
		String driverPath = PropertiesUtil.get("util", "CHROME_DRIVER");
		System.setProperty("webdriver.chrome.driver", driverPath);    	 	
		options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("window-size=1920x1080");
        options.addArguments("disable-gpu");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36");
        options.setProxy(null);
        HashMap<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.popups", 2);
 		prefs.put("profile.default_content_setting_values.images", 2);
 		prefs.put("profile.default_content_setting_values.plugins", 2);
 		prefs.put("profile.default_content_setting_values.notifications", 2);
 		prefs.put("profile.default_content_setting_values.auto_select_certificate", 2);
 		prefs.put("profile.default_content_setting_values.fullscreen", 2);
 		prefs.put("profile.default_content_setting_values.mouselock", 2);
 		prefs.put("profile.default_content_setting_values.mixed_script", 2);
 		prefs.put("profile.default_content_setting_values.media_stream", 2);
 		prefs.put("profile.default_content_setting_values.media_stream_mic", 2);
 		prefs.put("profile.default_content_setting_values.media_stream_camera", 2);
 		prefs.put("profile.default_content_setting_values.protocol_handlers", 2);
 		prefs.put("profile.default_content_setting_values.ppapi_broker", 2);
 		prefs.put("profile.default_content_setting_values.automatic_downloads", 2);
 		prefs.put("profile.default_content_setting_values.midi_sysex", 2);
 		prefs.put("profile.default_content_setting_values.push_messaging", 2);
 		prefs.put("profile.default_content_setting_values.ssl_cert_decisions", 2);
 		prefs.put("profile.default_content_setting_values.metro_switch_to_desktop", 2);
 		prefs.put("profile.default_content_setting_values.protected_media_identifier", 2);
 		prefs.put("profile.default_content_setting_values.app_banner", 2);
 		prefs.put("profile.default_content_setting_values.site_engagement", 2);
 		prefs.put("profile.default_content_setting_values.durable_storage", 2);
		options.setExperimentalOption("prefs", prefs);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		capabilities.setCapability("pageLoadStrategy", "none");
	}

	public MangoStoreVO crawl(StoreVO store) {
		
	    driver = new ChromeDriver(options);  
		wait = new WebDriverWait(driver, 4);
		
		MangoStoreVO mangoStoreVO = new MangoStoreVO();
		mangoStoreVO.setStore_no(store.getStore_no());
		mangoStoreVO.setMango_tel("");
		mangoStoreVO.setMango_kind("");
		mangoStoreVO.setMango_price("");
		mangoStoreVO.setMango_parking("");
		mangoStoreVO.setMango_time("");
		mangoStoreVO.setMango_break_time("");
		mangoStoreVO.setMango_last_order("");
		mangoStoreVO.setMango_holiday("");
		
		try {
			driver.get("https://www.mangoplate.com/search/" + store.getStore_name());
			
			Document doc = Jsoup.parse(driver.getPageSource());
			
			
			System.out.println("======================================================");
			//driver로 여는 첫 가게에서 배너 있으면 닫기
			if(doc.select(".dfp_ad_front_banner_wrap iframe") != null) { 
				WebElement iframe = driver.findElement(By.cssSelector(".dfp_ad_front_banner_wrap iframe"));
				driver.switchTo().frame(iframe).findElement(By.cssSelector(".ad_block_btn")).click();
				driver.switchTo().parentFrame();
			}
			
			//게시물 중 광주시 첫번째 가게 선택
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".center-croping.lazy")));
			List<WebElement> postlist = driver.findElements(By.cssSelector(".center-croping.lazy"));
			for (int i = 0; i < postlist.size(); i++) {
				if(!postlist.get(i).getAttribute("alt").contains("경기")
						&& postlist.get(i).getAttribute("alt").contains("광주")) {
					driver.findElements(By.cssSelector(".center-croping.lazy")).get(i).click();		 					
				
					//가게 설명 가져오기
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".info.no_menu")));
					List<WebElement> trElementList = driver.findElements(By.cssSelector(".info.no_menu tr"));
					
					
					for(WebElement ele : trElementList) {
						String th = ele.findElement(By.tagName("th")).getText();
						String td = ele.findElement(By.tagName("td")).getText();
						if(th.equals("전화번호")) {
							mangoStoreVO.setMango_tel(td);
						} else if(th.equals("음식 종류")) {
							mangoStoreVO.setMango_kind(td);
						} else if(th.equals("가격대")) {
							mangoStoreVO.setMango_price(td);
						} else if(th.equals("주차")) {
							mangoStoreVO.setMango_parking(td);
						} else if(th.equals("영업시간")) {
							mangoStoreVO.setMango_time(td);
						} else if(th.equals("쉬는시간")) {
							mangoStoreVO.setMango_break_time(td);
						} else if(th.equals("마지막주문")) {
							mangoStoreVO.setMango_last_order(td);
						} else if(th.equals("휴일")) {
							mangoStoreVO.setMango_holiday(td);
						}
					}
					
					
					System.out.println("======================================================");
					break;
				}
			}//가게 설명for
		} catch (TimeoutException e) {
			logger.info("TimeoutException");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.close();
		}
		
		return mangoStoreVO;
	}
}
