package com.yeollu.getrend.store.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeollu.getrend.crawler.instagram_Selenium_location_post;
import com.yeollu.getrend.store.dao.InstaLocationDAO;
import com.yeollu.getrend.store.dao.SearchedStoreDAO;
import com.yeollu.getrend.store.dao.StoreDAO;
import com.yeollu.getrend.store.util.map.core.Polygon;
import com.yeollu.getrend.store.util.map.model.Point;
import com.yeollu.getrend.store.util.preprocess.core.QueryStringSender;
import com.yeollu.getrend.store.vo.InstaLocationVO;
import com.yeollu.getrend.store.vo.InstaStoreInfoVO;
import com.yeollu.getrend.store.vo.InstaStoreVO;
import com.yeollu.getrend.store.vo.SearchedStoreVO;
import com.yeollu.getrend.store.vo.StoreVO;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private StoreDAO storeDAO;
	
	@Autowired
	private SearchedStoreDAO searchedDAO;
	
	@Autowired
	private InstaLocationDAO instaLocationDAO;
		
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		
		return "home";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String search(@RequestBody ArrayList<Point> points) {
		logger.info("search");
		long startTime = System.currentTimeMillis();
		
//		logger.info("{}", points);
		ArrayList<StoreVO> list = storeDAO.selectAllStores();
		
		ArrayList<StoreVO> selectedList = new ArrayList<StoreVO>();
		ArrayList<StoreVO> resultList = new ArrayList<StoreVO>();
				
		if(instaLocationDAO.countInstaLocations() == 0) {
			logger.info("set DB initial datas");
			selectedList = list;
			resultList = selectedList;
		} else {
			Polygon polygon = new Polygon();
			for(int i = 0; i < points.size(); i++) {
				polygon.addPoint(points.get(i));
			}
			
			for(int i = 0; i < list.size(); i++) {
				if(polygon.isContains(list.get(i).getStore_x(), list.get(i).getStore_y())) {
					selectedList.add(list.get(i));
				}	
			}
			
			resultList = selectedList;
			
			for(Iterator<StoreVO> iterator = selectedList.iterator(); iterator.hasNext(); ) {
				StoreVO store = iterator.next();
				if(!searchedDAO.isExistedSearchedStore(store.getStore_name())) {
					SearchedStoreVO searched = new SearchedStoreVO();
					searched.setStore_name(store.getStore_name());
					searchedDAO.insertSearchedStore(searched);	
					logger.info("{}", store.getStore_name());
				} else {
					if(instaLocationDAO.isExistedInstaLocation(store.getStore_no())) {
//						logger.info("insta location is existed");
						iterator.remove();
					} else {
//						logger.info("insta location is not existed");
					}					
				}
			}
		}
		
		for(StoreVO store : selectedList) {
			InstaStoreVO instaStore = QueryStringSender.send(store);
			if(instaStore != null) {
				ArrayList<InstaLocationVO> instaLocationList = instaStore.getInsta_locations().get(store.getStore_no());
				logger.info("{}", store.getStore_name());
				for(int i = 0; i < instaLocationList.size(); i++) {
					InstaLocationVO instaLocation = instaLocationList.get(i);
					if(!instaLocationDAO.isExistedInstaLocation(instaLocation.getLocation_pk())) {
						if(instaLocationDAO.insertInstaLocation(instaLocation) > 0) {
//							logger.info("insert insta location success");
						} else {
//							logger.info("insert insta location fail");
						}
					} else {
//						logger.info("insta location is existed");
					}
				}
			} else {
				logger.info("instaStore is null");
			}
		}
		
		
		
		String str = "";
	
		
		for(int i = 0; i < resultList.size(); i++) {
			ArrayList<InstaStoreInfoVO> instaLocationInfoList = storeDAO.selectInstaStoreInfo(resultList.get(i).getStore_no());
			logger.info("{}", instaLocationInfoList);
			if(instaLocationInfoList == null || instaLocationInfoList.size() == 0) {
				
			} else {
				for(int j = 0; j < instaLocationInfoList.size(); j++) {
					try {
						boolean flag = true;
						logger.info("location id: {}", instaLocationInfoList.get(j).getLocation_id());
						
						logger.info("로케이션 검색 시 최신 포스트에서 음식 사진 정보 얻기");
						instagram_Selenium_location_post ins = new instagram_Selenium_location_post();
						
						//썸네일  + 인기 포스트 10개
						ArrayList<String> _list = ins.location_post("https://www.instagram.com/explore/locations/" + instaLocationInfoList.get(j).getLocation_id());
						
						str += _list.stream()
								.map(n -> String.valueOf(n))
								.collect(Collectors.joining());

						
						logger.info("mango_store ");
						ArrayList<String> mango_store = new ArrayList<String>();
						System.setProperty("webdriver.chrome.driver", "C:/sts/sts-4.5.0.RELEASE/chromedriver.exe");    	 			 
						WebDriver driver = new ChromeDriver();  
						WebDriverWait wait = new WebDriverWait(driver, 3);
						
						logger.info("{}", instaLocationInfoList.get(j).getStore_name());
						driver.get("https://www.mangoplate.com/search/" + instaLocationInfoList.get(j).getStore_name());
						 
						//첫 시도에서 팝업창 있으면 닫기
						if(flag && By.cssSelector(".dfp_ad_front_banner_wrap iframe") != null) {
							WebElement iframe = driver.findElement(By.cssSelector(".dfp_ad_front_banner_wrap iframe"));
							driver.switchTo().frame(iframe).findElement(By.cssSelector(".ad_block_btn")).click();
							//driver.switchTo().defaultContent();
							driver.switchTo().parentFrame();
							flag = false;
						}
						 
						//게시물 중 첫번째 선택
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".center-croping.lazy")));
						driver.findElements(By.cssSelector(".center-croping.lazy")).get(0).click();		 
						 
						//가게 설명 가져오기
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".info.no_menu")));
						List<WebElement> text =driver.findElements(By.cssSelector(".info.no_menu td"));
						for (WebElement ele : text) {
							System.out.println(ele.getText());		
							str += ele.getText();
						}
						driver.close();
					} catch (Exception e) {
						logger.info("Exception");
						continue;
					}
				}
			}
		}
		
		long endTime = System.currentTimeMillis();
		long diff = (endTime - startTime) / 1000;
		logger.info("걸린 시간 : {}", diff);
		
		logger.info("{}", str);
		return str;
	}
}
