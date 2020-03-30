package com.yeollu.getrend.crawler;

import java.util.ArrayList;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yeollu.getrend.store.util.preprocess.core.StringPreprocessor;
import com.yeollu.getrend.store.vo.InstaImageVO;
import com.yeollu.getrend.store.vo.PostImageVO;
import com.yeollu.getrend.util.PropertiesUtil;

public class instagram_Selenium_location_post3 {
	
	private static final Logger logger = LoggerFactory.getLogger(instagram_Selenium_location_post3.class);
	
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
	
	public instagram_Selenium_location_post3() {

	}
		
	public InstaImageVO location_post(String location) {
		driver = new ChromeDriver(options);
		logger.info("드라이버 실행 : {}", location);
		wait = new WebDriverWait(driver, 4);
		
		InstaImageVO instaImage = new InstaImageVO();
		try {
			
			driver.get("https://www.instagram.com/explore/locations/" + location);
			
			Document doc = Jsoup.parse(driver.getPageSource());
			
			System.out.println("======================================================");
//			System.out.println("가게 :  " + driver.getTitle());
			
			//대표 썸네일
//			Thread.sleep(1000);
			Thread.sleep(50);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".ECCnW")));
			String thum = doc.selectFirst(".ECCnW").attr("src");
//			System.out.println("대표 사진 : " + thum);
			if(thum != null && !thum.equals("")) {
				instaImage.setRepImg(thum);
			}
			
			Actions action = new Actions(driver);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".FFVAD")));
			List<WebElement> list_01 = driver.findElements(By.cssSelector(".FFVAD"));
			
			//좋아요 10개
			ArrayList<String> likeList = new ArrayList<String>();
			for (int j = 0; j < 10 ; j++) {
	        	try {
//		      		Thread.sleep(2000);
		        	Thread.sleep(50);
		        	action.moveToElement(list_01.get(j)).perform();
	        		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".qn-0x")));
	        		String like = driver.findElement(By.cssSelector(".qn-0x")).getText().split("\n")[0];
	        		
	        		if(like != null && !like.equals("")) {
	        			System.out.println(j + 1 + "번 like : " + like);	
	        			likeList.add(like);
	        		}
	        	} catch (TimeoutException e) {
	        		logger.info("TimeoutException");
				} catch (IndexOutOfBoundsException e) {
					break;
				} catch (Exception e) {
					e.printStackTrace();
				}
	      	}		        
	        
			//인기게시글 10개
			ArrayList<String> srcsetList = new ArrayList<String>();
			for (int j = 0; j < 10 ; j++) {
				try {
					String img_srcset = list_01.get(j).getAttribute("srcset").split(" ")[0];
	       			if(img_srcset != null && !img_srcset.equals("")) {
	       				System.out.println(j + 1 + "번 img srcset : " + img_srcset);	
	       				srcsetList.add(img_srcset);
	       			}
				} catch (IndexOutOfBoundsException e) {
					break;
				} catch (Exception e) {
					e.printStackTrace();
				}
	      	}
	       	
	       	ArrayList<PostImageVO> postImgList = new ArrayList<PostImageVO>();
	       	for(int j = 0; j < likeList.size(); j++) {
	       		PostImageVO postImage = new PostImageVO();
	       		postImage.setLike(Integer.parseInt(StringPreprocessor.stringReplace(likeList.get(j))));
	       		postImage.setImgUrl(srcsetList.get(j));
	       		postImgList.add(postImage);
	       	}
	       	
	       	instaImage.setPostImgList(postImgList);
	       	 
	       	System.out.println("======================================================");
	       	 
		} catch (TimeoutException e) {
			logger.info("TimeoutException");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.close();
//			driver.quit();
		}
		
		return instaImage;
	}
}
