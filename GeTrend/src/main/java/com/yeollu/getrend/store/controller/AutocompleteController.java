package com.yeollu.getrend.store.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.yeollu.getrend.crawler.CrawlerExecutor;
import com.yeollu.getrend.mango.dao.MangoStoreDAO;
import com.yeollu.getrend.mango.vo.MangoStoreVO;
import com.yeollu.getrend.store.dao.InstaLocationDAO;
import com.yeollu.getrend.store.dao.StoreDAO;
import com.yeollu.getrend.store.util.preprocess.core.QueryStringSender;
import com.yeollu.getrend.store.vo.InstaImageVO;
import com.yeollu.getrend.store.vo.InstaLocationVO;
import com.yeollu.getrend.store.vo.InstaStoreInfoVO;
import com.yeollu.getrend.store.vo.InstaStoreVO;
import com.yeollu.getrend.store.vo.MangoStoreInfoVO;
import com.yeollu.getrend.store.vo.StoreVO;

@Controller
@RequestMapping(value="/autocomplete")
public class AutocompleteController {
	
	private static final Logger logger = LoggerFactory.getLogger(AutocompleteController.class);
	
	@Autowired
	private StoreDAO storeDAO;
	
	@Autowired
	private InstaLocationDAO instaLocationDAO;
	
	@Autowired
	private MangoStoreDAO mangoStoreDAO;
	

	@RequestMapping(value = "/autocompleteForm", method = RequestMethod.GET)
	public String autocompleteForm() {
		return "autocomplete_test";
	}
	@RequestMapping(value = "/source", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String source(String param) {    
	    logger.info("param {}", param);
	    ArrayList<String> array = new ArrayList<String>();
		array.addAll(storeDAO.autoStoreCate2_01());
		array.addAll(storeDAO.autoStoreCate2_02());
		array.addAll(storeDAO.autoStoreCate2_03());
		array.addAll(storeDAO.autoStoreAdr());
		array.addAll(storeDAO.autoStoreName());
		
	    ArrayList<String> list = new ArrayList<String>();
	    int j = 0;
		for (int i = 0; i < array.size(); i++) {
	    	if(array.get(i).contains(param)) {
				j++;
				if(j > 10) {
					logger.info("검색 수 {}", i);
					break;
				}
				list.add(array.get(i));
			}
		}
		logger.info("list {}",list);
		
		Gson gson = new Gson();
	    return gson.toJson(list);
	}

	@RequestMapping(value = "/keyword", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<InstaStoreInfoVO> keyword(String keyword) {
		logger.info("keyword {}", keyword);
		long startTime = System.currentTimeMillis();
		
		ArrayList<StoreVO> list = storeDAO.searchStoresByTerm(keyword);
		logger.info("{}", list.size());
		
		ArrayList<InstaStoreVO> instaStoreList = new ArrayList<InstaStoreVO>();
		for(StoreVO store : list) {
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
		
		// 망고플레이트 정보 추가
		ArrayList<MangoStoreInfoVO> mangoStoreInfoList = new ArrayList<MangoStoreInfoVO>();
		for(InstaStoreVO instaStore : instaStoreList) {
			MangoStoreInfoVO mangoStoreInfo = new MangoStoreInfoVO();
//			mangoStoreInfo = mangoStoreDAO.selectMangoStoreInfoByStoreNo(instaStore.getStore_no());
			mangoStoreInfoList.add(mangoStoreInfo);
		}
		
//		ArrayList<MangoStoreVO> mangoStoreList = new ArrayList<MangoStoreVO>();
//		for(InstaStoreVO instaStore : instaStoreList) {
//			MangoStoreVO mangoStore = new MangoStoreVO();
//			mangoStore = mangoStoreDAO.selectMangoStoreByStoreNo(instaStore.getStore_no());
//			mangoStoreList.add(mangoStore);
//		}
		
		if(instaStoreList.size() > 3) {
			instaStoreList = new ArrayList<InstaStoreVO> (instaStoreList.subList(0, 3));
		}
		
		ArrayList<String> locationList = new ArrayList<String>();
		for(int i = 0; i < instaStoreList.size(); i++) {
			locationList.add(instaStoreList.get(i).getLocation_id());
		}
		
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
		CrawlerExecutor.killChromeDriver();
		
		ArrayList<InstaStoreInfoVO> instaStoreInfoList = new ArrayList<InstaStoreInfoVO>();
		try {
			for(int i = 0; i < instaStoreList.size(); i++) {
				InstaStoreInfoVO instaStoreInfo = new InstaStoreInfoVO();
				instaStoreInfo.setInstaStore(instaStoreList.get(i));
//				instaStoreInfo.setMangoStore(mangoStoreList.get(i));
				instaStoreInfo.setMangoStoreInfo(mangoStoreInfoList.get(i));
				
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
		
		
		return instaStoreInfoList;
	}
	
	
	
}
