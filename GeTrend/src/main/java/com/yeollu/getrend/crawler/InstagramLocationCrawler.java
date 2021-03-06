package com.yeollu.getrend.crawler;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yeollu.getrend.store.util.preprocess.StringPreprocessor;
import com.yeollu.getrend.store.vo.InstaImageVO;
import com.yeollu.getrend.util.PropertiesUtil;

/**
 * @Class 	: InstagramLocationCrawler.java
 * @Package	: com.yeollu.getrend.crawler
 * @Project : GeTrend
 * @Author	: 조은채
 * @Since	: 2020. 3. 26.
 * @Version	: 1.0
 * @Desc	: 인스타그램 웹 크롤링 작업을 수행한다.
 */
public class InstagramLocationCrawler {
	
	
	/**
	 * Fields
	 */
	private static final Logger logger = LoggerFactory.getLogger(InstagramLocationCrawler.class);
	private static ChromeOptions options;
	private WebDriver driver;
	private FluentWait<WebDriver> wait;
	
	static {
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
	
	/**
	 * Constructor
	 */
	public InstagramLocationCrawler() {

	}
		
	/**
	 * @Method	: crawl
	 * @Return	: ArrayList<InstaImageVO>
	 * @Author	: 조은채
	 * @Since	: 2020. 3. 26.
	 * @Version	: 1.0
	 * @Desc	: 매개변수로 넘겨받은 location_id를 이용해 인스타그램 웹 크롤링 작업을 수행한다.
	 * @param store_no
	 * @param location_id
	 */
	public ArrayList<InstaImageVO> crawl(String store_no, String location_id) {
		driver = new ChromeDriver(options);
		logger.info("드라이버 실행 : {}", location_id);
//		wait = new WebDriverWait(driver, 4);
		wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(5000))
				.ignoring(NoSuchElementException.class)
				.ignoring(TimeoutException.class)
				.ignoring(StaleElementReferenceException.class)
				.ignoring(WebDriverException.class);
		
		ArrayList<InstaImageVO> instaImageList = new ArrayList<InstaImageVO>();
		
		try {
			
			driver.get("https://www.instagram.com/explore/locations/" + location_id);
			
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
				InstaImageVO instaImage = new InstaImageVO();
				instaImage.setStore_no(store_no);
				instaImage.setImage_type("profile");
				instaImage.setImage_url(thum);
				instaImage.setImage_like(0);
				instaImageList.add(instaImage);
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
	        		
	        		if(like.contains("천")) {
	        			int idx = like.indexOf("천");
	        			like = like.substring(0, idx);
	        		}
	        		if(like != null && !like.equals("")) {
	        			System.out.println(j + 1 + "번 like : " + like);	
	        			likeList.add(like);
	        		}
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
//					String img_srcset = list_01.get(j).getAttribute("srcset").split(" ")[0];
					String img_srcset = list_01.get(j).getAttribute("src").split(" ")[0];
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
	       	
			for(int j = 0; j < likeList.size(); j++) {
				InstaImageVO instaImage = new InstaImageVO();
				instaImage.setStore_no(store_no);
				instaImage.setImage_type("common");
				instaImage.setImage_url(srcsetList.get(j));
				instaImage.setImage_like(Integer.parseInt(StringPreprocessor.stringReplace(likeList.get(j))));
				instaImageList.add(instaImage);
			}
	       	 
	       	System.out.println("======================================================");
	       	 
		} catch (TimeoutException e) {
			// ignore
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.close();
//			driver.quit();
		}
		
		return instaImageList;
	}
}
