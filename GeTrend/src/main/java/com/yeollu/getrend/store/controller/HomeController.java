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

import com.yeollu.getrend.crawler.instagram_location_post;
import com.yeollu.getrend.store.dao.InstaLocationDAO;
import com.yeollu.getrend.store.dao.SearchedStoreDAO;
import com.yeollu.getrend.store.dao.StoreDAO;
import com.yeollu.getrend.store.util.map.core.Polygon;
import com.yeollu.getrend.store.util.map.model.Point;
import com.yeollu.getrend.store.util.preprocess.core.QueryStringSender;
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
		for(InstaStoreVO instaStore : instaStoreList) {
			try {
				logger.info("location id: {}", instaStore.getLocation_id());
				
//				logger.info("로케이션 검색 시 최신 포스트에서 음식 사진 정보 얻기");
				instagram_location_post ins = new instagram_location_post();
				
//				썸네일  + 인기 포스트 10개
				ArrayList<String> urlList = ins.crawl("https://www.instagram.com/explore/locations/" + instaStore.getLocation_id());
				
				if(urlList != null && urlList.size() != 0) {
//					logger.info("{}", urlList.get(0));
//					logger.info("{}", urlList.subList(0, urlList.size() - 1));
					InstaStoreInfoVO instaStoreInfo = new InstaStoreInfoVO();
					instaStoreInfo.setStore_no(instaStore.getStore_no());
					instaStoreInfo.setStore_name(instaStore.getStore_name());
					instaStoreInfo.setStore_name2(instaStore.getStore_name2());
					instaStoreInfo.setLocation_id(instaStore.getLocation_id());
					instaStoreInfo.setStore_adr1(instaStore.getStore_adr1());
					instaStoreInfo.setStore_adr2(instaStore.getStore_adr2());
					instaStoreInfo.setStore_cate1(instaStore.getStore_cate1());
					instaStoreInfo.setStore_cate2(instaStore.getStore_cate2());
					instaStoreInfo.setStore_cate3(instaStore.getStore_cate3());
					instaStoreInfo.setStore_dem(instaStore.getStore_dem());
					instaStoreInfo.setStore_x(instaStore.getStore_x());
					instaStoreInfo.setStore_y(instaStore.getStore_y());
					instaStoreInfo.setProfile_url(urlList.get(0));
					instaStoreInfo.setImgList(new ArrayList<String> (urlList.subList(1, urlList.size())));
					instaStoreInfoList.add(instaStoreInfo);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
			
		long endTime = System.currentTimeMillis();
		long diff = (endTime - startTime) / 1000;
		logger.info("걸린 시간 : {}", diff);
		
		logger.info("{}", instaStoreInfoList);
		logger.info("{}", instaStoreInfoList.size());
		return instaStoreInfoList;
	}
	
	
	
	
	
	
	
	
	
	
	

}
