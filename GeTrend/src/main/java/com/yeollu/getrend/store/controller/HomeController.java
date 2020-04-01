package com.yeollu.getrend.store.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yeollu.getrend.crawler.CrawlerExecutor;
import com.yeollu.getrend.crawler.instagram_Selenium_location_post2;
import com.yeollu.getrend.crawler.instagram_Selenium_location_post3;
import com.yeollu.getrend.crawler.mango_store_info;
import com.yeollu.getrend.crawler.mango_store_info2;
import com.yeollu.getrend.store.dao.InstaLocationDAO;
import com.yeollu.getrend.store.dao.MangoStoreDAO;
import com.yeollu.getrend.store.dao.SearchedStoreDAO;
import com.yeollu.getrend.store.dao.StoreDAO;
import com.yeollu.getrend.store.util.map.core.Polygon;
import com.yeollu.getrend.store.util.map.model.Point;
import com.yeollu.getrend.store.util.preprocess.core.QueryStringSender;
import com.yeollu.getrend.store.vo.InstaImageVO;
import com.yeollu.getrend.store.vo.InstaLocationVO;
import com.yeollu.getrend.store.vo.InstaStoreInfoVO;
import com.yeollu.getrend.store.vo.InstaStoreVO;
import com.yeollu.getrend.store.vo.MangoStoreVO;
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
	
	@Autowired
	private MangoStoreDAO mangoStoreDAO;
		
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		
		return "home";
	}

	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<InstaStoreInfoVO> search(@RequestBody ArrayList<Point> points) {
		logger.info("search");
		long startTime = System.currentTimeMillis();
		
//		DB에 저장된 모든 상가 리스트를 가져옴
		ArrayList<StoreVO> list = storeDAO.selectAllStores();
//		ArrayList<StoreVO> list = storeDAO.selectStoresWithMangoStores();
		
		
		ArrayList<StoreVO> selectedList = new ArrayList<StoreVO>();
				
//		View로부터 넘겨받은 다각형의 꼭지점을 이용해 좌표상의 다각형 생성하여 판별
		Polygon polygon = new Polygon();
		for(int i = 0; i < points.size(); i++) {
			polygon.addPoint(points.get(i));
		}
		
//		상가 리스트(list) 중에서 다각형 내부에 존재하는 상가들만 추출 => selected list
		for(int i = 0; i < list.size(); i++) {
			if(polygon.isContains(list.get(i).getStore_x(), list.get(i).getStore_y())) {
				selectedList.add(list.get(i));
			}	
		}
		
//		인스타그램에 쿼리스트링을 보내 상가의 위치 정보 수집하여 location_id를 하나 리턴받아
//		InstaStore 객체 생성하여 instaStoreList에 수집
		ArrayList<InstaStoreVO> instaStoreList = new ArrayList<InstaStoreVO>();
		for(StoreVO store : selectedList) {
			String location_id = QueryStringSender.send(store);
			if(location_id == null || location_id.equals("")) {
			} else {
				if(!instaLocationDAO.isExistedInstaLocation(location_id)) {
					InstaLocationVO instaLocation = new InstaLocationVO();
					instaLocation.setLocation_id(location_id);
					instaLocation.setStore_no(store.getStore_no());
					instaLocationDAO.insertInstaLocation(instaLocation);
				}
				InstaStoreVO instaStore = storeDAO.selectInstaStore(store.getStore_no());
				if(instaStore != null) {
					instaStore.setLocation_id(location_id);
					instaStoreList.add(instaStore);
				}
			}
		}
		logger.info("{}", instaStoreList);
		
		
//		망고플레이트 정보 추가
		ArrayList<MangoStoreVO> mangoStoreList = new ArrayList<MangoStoreVO>();
		for(InstaStoreVO instaStore : instaStoreList) {
			MangoStoreVO mangoStore = new MangoStoreVO();
			mangoStore = mangoStoreDAO.selectMangoStoreByStoreNo(instaStore.getStore_no());
			mangoStoreList.add(mangoStore);
		}
		
		
//		if(instaStoreList.size() > 3) {
//			instaStoreList = new ArrayList<InstaStoreVO> (instaStoreList.subList(0, 3));
//		}
		
		
		ArrayList<String> locationList = new ArrayList<String>();
		for(int i = 0; i < instaStoreList.size(); i++) {
			locationList.add(instaStoreList.get(i).getLocation_id());
		}
		
//		인스타그램 크롤링 요청
		ArrayList<InstaImageVO> instaImageList = new ArrayList<InstaImageVO>();
		ArrayList<CrawlerExecutor> crawlerExecutorList = new ArrayList<CrawlerExecutor>();
		for(String location : locationList) {
			CrawlerExecutor crawlerExecutor = new CrawlerExecutor();
			crawlerExecutor.setLocation(location);
			new Thread(crawlerExecutor, "crawling :  " + location).start();
			crawlerExecutorList.add(crawlerExecutor);
		}
		for(CrawlerExecutor crawlerExecutor : crawlerExecutorList) {
			instaImageList.add(crawlerExecutor.getInstaImage());
		}
		
		
//		View로 보낼 최종 객체 리스트
		ArrayList<InstaStoreInfoVO> instaStoreInfoList = new ArrayList<InstaStoreInfoVO>();
		try {
			for(int i = 0; i < instaStoreList.size(); i++) {
				InstaStoreInfoVO instaStoreInfo = new InstaStoreInfoVO();
				instaStoreInfo.setInstaStore(instaStoreList.get(i));
				instaStoreInfo.setMangoStore(mangoStoreList.get(i));
				
				if(instaImageList.size() > i) {
					instaStoreInfo.setInstaImage(instaImageList.get(i));					
				} else {
					instaStoreInfo.setInstaImage(null);
				}
				
				instaStoreInfoList.add(instaStoreInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		long endTime = System.currentTimeMillis();
		long diff = (endTime - startTime) / 1000;
		logger.info("걸린 시간 : {}", diff);
		
		try {
			Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe /t");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return instaStoreInfoList;
	}
	
	
	
	
	
	
	
	
	
	
	

}
