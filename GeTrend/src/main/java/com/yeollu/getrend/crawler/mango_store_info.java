package com.yeollu.getrend.crawler;

import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yeollu.getrend.util.PropertiesUtil;

public class mango_store_info {
	
private static final Logger logger = LoggerFactory.getLogger(mango_store_info.class);
	
	//WebDriver
	private WebDriver driver;

	public void crawl(String store_name){
		
//		 System.setProperty("webdriver.chrome.driver", "C:/stsinstall/sts-4.5.0.RELEASE/chromedriver.exe");    	 	
		String driverPath = PropertiesUtil.get("util", "CHROME_DRIVER");
		System.setProperty("webdriver.chrome.driver", driverPath);  
		 ChromeOptions options = new ChromeOptions();
//		 options.addArguments("headless");
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
       
         driver = new ChromeDriver(options);  
		 WebDriverWait wait = new WebDriverWait(driver, 10);				 
		 Document doc = Jsoup.parse(driver.getPageSource()); 
		
	
		  try {		
			  
			  //1 driver 3 가게
			  for(int a = 0; a < 3 ; a++) {
				  
				  driver.get("https://www.mangoplate.com/search/" + store_name);
				  
				  //driver로 여는 첫 가게에서 배너 있으면 닫기
				  if(a == 0 && doc.select(".dfp_ad_front_banner_wrap iframe") != null) { 		
					 WebElement iframe = driver.findElement(By.cssSelector(".dfp_ad_front_banner_wrap iframe"));
					 driver.switchTo().frame(iframe).findElement(By.cssSelector(".ad_block_btn")).click();
					 driver.switchTo().parentFrame();
				 }
				 
					 
				 //게시물 중 광주시 첫번째 가게 선택
				 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".center-croping.lazy")));
				 List<WebElement> postlist = driver.findElements(By.cssSelector(".center-croping.lazy"));
				 for (int i = 0; i < postlist.size(); i++) {
					if(postlist.get(i).getAttribute("alt").contains("광주시")) {
						 driver.findElements(By.cssSelector(".center-croping.lazy")).get(i).click();		 					
					
						 //가게 설명 가져오기
						 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".info.no_menu")));
						 List<WebElement> text =driver.findElements(By.cssSelector(".info.no_menu td"));
						 System.out.println("======================================================");
						 for (WebElement ele : text) {
							 System.out.println(ele.getText());			
						 }
						 System.out.println("======================================================");
						 break;
					}
				 }//가게 설명for
		   }//가게 갯수 for
			 
		  } catch(Exception e) {
			 	e.printStackTrace();
		}
		
		driver.close(); 			   
	}

	
}
