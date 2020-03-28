package com.yeollu.getrend.crawler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yeollu.getrend.store.util.preprocess.core.StringPreprocessor;
import com.yeollu.getrend.store.vo.InstaImageVO;

public class instagram_Selenium_location_post2 {
	
	private static final Logger logger = LoggerFactory.getLogger(instagram_Selenium_location_post2.class);
	
	//WebDriver
	private static WebDriver driver;
	
	private static ChromeOptions options;
	
	private static WebDriverWait wait;
	
	static {
//		System.setProperty("webdriver.chrome.driver", "C:/stsinstall/sts-4.5.0.RELEASE/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "C:/StsInstall/sts-4.5.0.RELEASE/chromedriver.exe");    	 	
		options = new ChromeOptions();
//		options.addArguments("headless");
		options.addArguments("window-size=1920x1080");
        options.addArguments("disable-gpu");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36");
        options.setProxy(null);
        HashMap<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.cookies", 2);
		prefs.put("profile.default_content_setting_values.images", 2);
		prefs.put("profile.default_content_setting_values.plugins", 2);
		prefs.put("profile.default_content_setting_values.popups", 2);
		prefs.put("profile.default_content_setting_values.geolocation", 2);
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
	
	private instagram_Selenium_location_post2() {
		
	}
	
	private static class InnerInstance_instagram_Selenium_location_post2 {
		private static final instagram_Selenium_location_post2 instance = new instagram_Selenium_location_post2();
	}
	
	public static instagram_Selenium_location_post2 getInstance() {
		return InnerInstance_instagram_Selenium_location_post2.instance;
	}
		
	public ArrayList<InstaImageVO> location_post(ArrayList<String> locationList) throws Exception {
		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, 4);
		
		ArrayList<InstaImageVO> imgList = new ArrayList<InstaImageVO>();
		try {
			for (int i = 0; i < locationList.size(); i++) {
				InstaImageVO img = new InstaImageVO();
				
				if(i > 0) {
					((JavascriptExecutor) driver).executeScript("window.open()");
				}
				
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(i));
				driver.get("https://www.instagram.com/explore/locations/" + locationList.get(i));
				
				Document doc = Jsoup.parse(driver.getPageSource());
				
				System.out.println("======================================================");
//				System.out.println("가게 :  " + driver.getTitle());
				
				//대표 썸네일
//				Thread.sleep(1000);
				Thread.sleep(50);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".ECCnW")));
				String thum = doc.selectFirst(".ECCnW").attr("src");
//				System.out.println("대표 사진 : " + thum);
				if(thum != null && !thum.equals("")) {
					img.setRepImg(thum);
				}
				
				
				Actions action = new Actions(driver);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".FFVAD")));
				List<WebElement> list_01 = driver.findElements(By.cssSelector(".FFVAD"));
				
				ArrayList<String> likeList = new ArrayList<String>();
				//좋아요 10개
		        for (int j = 0; j < 10 ; j++) {
//		      		Thread.sleep(2000);
		        	Thread.sleep(50);
		      		action.moveToElement(list_01.get(j)).perform();
		          	wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".qn-0x")));
		          	String like = driver.findElement(By.cssSelector(".qn-0x")).getText().split("\n")[0];
		          	if(like != null && !like.equals("")) {
		          		System.out.println(j + 1 + "번 like : " + like);	
		          		likeList.add(like);
		          	}
		      	}
		         
		        ArrayList<String> srcsetList = new ArrayList<String>();
		      	//인기게시글 10개
		       	for (int j = 0; j < 10 ; j++) {
		       		String img_srcset = list_01.get(j).getAttribute("srcset").split(" ")[0];
		          	if(img_srcset != null && !img_srcset.equals("")) {
		          		System.out.println(j + 1 + "번 img srcset : " + img_srcset);	
		          		srcsetList.add(img_srcset);
		          	}
		      	}
		       	
		       	ArrayList<String> likeAndImgList = new ArrayList<String>();
		       	for(int j = 0; j < 10; j++) {
		       		likeAndImgList.add(srcsetList.get(j));
		       		likeAndImgList.add(StringPreprocessor.stringReplace(likeList.get(j)));
		       	}
		       	
		       	img.setLikeAndImgList(likeAndImgList);
		       	 
		       	System.out.println("======================================================");
		       	 
		       	imgList.add(img);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		driver.close();
		driver.quit();
		return imgList;
	}
}
