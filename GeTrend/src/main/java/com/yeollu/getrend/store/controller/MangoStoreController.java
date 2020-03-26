package com.yeollu.getrend.store.controller;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MangoStoreController {
	
	private static final Logger logger = LoggerFactory.getLogger(MangoStoreController.class);

	///망고 플레이트 가게 소개
		@RequestMapping(value="/mango_store",method=RequestMethod.GET)
		public String mango_store(Model model) throws Exception{
			 logger.info("mango_store ");
			 ArrayList<String> mango_store = new ArrayList<String>();
			 System.setProperty("webdriver.chrome.driver", "C:/sts/sts-4.5.0.RELEASE/chromedriver.exe");    	 			 
			 WebDriver driver = new ChromeDriver();  
			 WebDriverWait wait = new WebDriverWait(driver, 30);
			 
			 for(int i = 0; i < 5 ; i++) {
				 driver.get("https://www.mangoplate.com/search/란도리");
				 
				 //첫 시도에서 팝업창 있으면 닫기
				 if(i == 0 && By.cssSelector(".dfp_ad_front_banner_wrap iframe") != null) {
					
					 WebElement iframe = driver.findElement(By.cssSelector(".dfp_ad_front_banner_wrap iframe"));
					 driver.switchTo().frame(iframe).findElement(By.cssSelector(".ad_block_btn")).click();
					 //driver.switchTo().defaultContent();
					 driver.switchTo().parentFrame();
				 }
				 
				 //게시물 중 첫번째 선택
				 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".center-croping.lazy")));
				 driver.findElements(By.cssSelector(".center-croping.lazy")).get(0).click();		 
				 
				 //가게 설명 가져오기
				 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".info.no_menu")));
				 List<WebElement> text =driver.findElements(By.cssSelector(".info.no_menu td"));
				 for (WebElement ele : text) {
					 System.out.println(ele.getText());			
				 }
			 
			 }
			 driver.close();

	//주차공간, 휴일, 운영시간도 있음.		 
//			 광주광역시 서구 상무민주로32번길 3-22
//			 지번 광주시 서구 쌍촌동 1303-4
//			 062-371-0170
//			 카페 / 디저트
//			 만원 미만
//			 주차공간없음
//			 10:00 - 22:00
//			 일
			 
	//란도리 - 주차공간, 휴일, 운영시간은 없음		 
//			 광주광역시 동구 동명로 45-1
//			 지번 광주시 동구 동명동 177-14
//			 062-232-1513
//			 정통 일식 / 일반 일식
//			 만원-2만원
			 
			 return "home";
		}
}
