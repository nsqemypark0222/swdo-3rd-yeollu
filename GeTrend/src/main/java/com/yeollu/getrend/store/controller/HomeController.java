package com.yeollu.getrend.store.controller;

import java.util.ArrayList;
import java.util.Locale;

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
import com.yeollu.getrend.store.vo.InstaImageVO;
import com.yeollu.getrend.store.vo.InstaLocationVO;
import com.yeollu.getrend.store.vo.InstaStoreInfoVO;
import com.yeollu.getrend.store.vo.InstaStoreVO;
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
	
	@RequestMapping(value = "/likeForm", method = RequestMethod.GET)
	public String likeForm() {
		return "like_test";
	}
	
	@RequestMapping(value = "/crawlForm", method = RequestMethod.GET)
	public String crawlForm() {
		return "crawl_test";
	}
	
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<InstaStoreInfoVO> search(@RequestBody ArrayList<Point> points) {
		logger.info("search");
		long startTime = System.currentTimeMillis();
		
//		DB에 저장된 모든 상가 리스트를 가져옴
		ArrayList<StoreVO> list = storeDAO.selectAllStores();
		
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
		
		ArrayList<InstaStoreVO> instaStoreList = new ArrayList<InstaStoreVO>();
//		인스타그램에 쿼리스트링을 보내 상가의 위치 정보 수집하여 location_id를 하나 리턴받아
//		InstaStore 객체 생성하여 instaStoreList에 수집
		for(StoreVO store : selectedList) {
			String location_id = QueryStringSender.send(store);
			if(location_id == null || location_id.equals("")) {
			} else {
				logger.info("{}", location_id);
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
//		logger.info("{}", selectedList);
		
//		View로 보낼  최종 리스트
		ArrayList<InstaStoreInfoVO> instaStoreInfoList = new ArrayList<InstaStoreInfoVO>();
		
		if(instaStoreList.size() > 3) {
			instaStoreList = new ArrayList<InstaStoreVO> (instaStoreList.subList(0, 3));
		}
		
//		인스타그램 크롤링 요청
		instagram_Selenium_location_post ins = instagram_Selenium_location_post.getInstance();
		
		ArrayList<String> locationList = new ArrayList<String>();
		for(int i = 0; i < instaStoreList.size(); i++) {
			locationList.add(instaStoreList.get(i).getLocation_id());
		}
		try {
			ArrayList<InstaImageVO> imgList = ins.location_post(locationList);
			logger.info("{}", imgList.size());
			
			logger.info("{}", imgList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		long endTime = System.currentTimeMillis();
		long diff = (endTime - startTime) / 1000;
		logger.info("걸린 시간 : {}", diff);
		
		logger.info("{}", instaStoreInfoList);
		logger.info("{}", instaStoreInfoList.size());
		return instaStoreInfoList;
	}
	
	
	
	
	
	
	
	
	
	
	

}
