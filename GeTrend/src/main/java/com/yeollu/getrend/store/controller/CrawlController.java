package com.yeollu.getrend.store.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yeollu.getrend.crawler.CrawlerExecutor;
import com.yeollu.getrend.crawler.CrawlerExecutorForMango;
import com.yeollu.getrend.crawler.mango_store_info;
import com.yeollu.getrend.crawler.mango_store_info2;
import com.yeollu.getrend.store.dao.InstaLocationDAO;
import com.yeollu.getrend.store.dao.MangoStoreDAO;
import com.yeollu.getrend.store.dao.SearchedStoreDAO;
import com.yeollu.getrend.store.dao.StoreDAO;
import com.yeollu.getrend.store.vo.MangoStoreVO;
import com.yeollu.getrend.store.vo.StoreVO;

@Controller
@RequestMapping(value="/crawl")
public class CrawlController {
	
	private static final Logger logger = LoggerFactory.getLogger(CrawlController.class);
	
	@Autowired
	private StoreDAO storeDAO;
	
	@Autowired
	private MangoStoreDAO mangoStoreInfoDAO;


	@RequestMapping(value = "/mango_store_info", method = RequestMethod.POST)
	public String mango_store_info(String store_name) {
		logger.info("망플 가게 정보");
		long startTime = System.currentTimeMillis();
		mango_store_info mango = new mango_store_info();
		mango.crawl(store_name);
		long endTime = System.currentTimeMillis();
		long diff = (endTime - startTime) / 1000;
		logger.info("걸린 시간 : {}", diff);
		return "crawl_test";
	}
	
	@RequestMapping(value = "/mango_plate_crawl", method = RequestMethod.GET)
	public String mango_plate_crawl() {
		long startTime = System.currentTimeMillis();
		mango_store_info2 mango = new mango_store_info2();
		
		ArrayList<StoreVO> _list = storeDAO.selectAllStores();
		ArrayList<StoreVO> list = new ArrayList<StoreVO>(_list.subList(0, 10));
//		ArrayList<StoreVO> list = new ArrayList<StoreVO>(_list.subList(10, 20));
//		ArrayList<StoreVO> list = new ArrayList<StoreVO>(_list.subList(20, 30));
//		ArrayList<StoreVO> list = new ArrayList<StoreVO>(_list.subList(30, 50));
//		ArrayList<StoreVO> list = new ArrayList<StoreVO>(_list.subList(50, 100));
		
		ArrayList<MangoStoreVO> mangoStoreList = new ArrayList<MangoStoreVO>();
		ArrayList<CrawlerExecutorForMango> crawlerExecutorForMangoList = new ArrayList<CrawlerExecutorForMango>();
		
		for(StoreVO store : list) {
			try {
				CrawlerExecutorForMango crawlerExecutorForMango = new CrawlerExecutorForMango();
				crawlerExecutorForMango.setStore(store);
				new Thread(crawlerExecutorForMango, "crawling : " + store.getStore_name()).start();
				crawlerExecutorForMangoList.add(crawlerExecutorForMango);
			} catch (Exception e) {
				continue;
			}
		}
		for(CrawlerExecutorForMango crawlerExecutorForMango : crawlerExecutorForMangoList) {
			mangoStoreList.add(crawlerExecutorForMango.getMangoStore());
		}
		for(MangoStoreVO mangoStore : mangoStoreList) {
			try {
				logger.info("{}", mangoStore.getStore_no());
				int cnt = mangoStoreInfoDAO.insertMangoStore(mangoStore);
				if(cnt > 0) {
					logger.info("추가 성공");
				} else {
					logger.info("추가 실패");
				}
			} catch (Exception e) {
				continue;
			}
		}
		
		
		long endTime = System.currentTimeMillis();
		long diff = (endTime - startTime) / 1000;
		logger.info("걸린 시간 : {}", diff);
		return "crawl_test";
	}
	
}
