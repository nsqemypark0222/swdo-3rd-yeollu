package com.yeollu.getrend.store.controller;

import java.io.IOException;
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

	@RequestMapping(value = "/crawlForm", method = RequestMethod.GET)
	public String crawlForm() {
		return "crawl_test";
	}


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
		
//		ArrayList<StoreVO> __list = storeDAO.selectAllStores();
//		ArrayList<StoreVO> _list = new ArrayList<StoreVO>(__list.subList(0, 10));
//		ArrayList<StoreVO> _list = new ArrayList<StoreVO>(__list.subList(10, 100));
//		ArrayList<StoreVO> _list = new ArrayList<StoreVO>(__list.subList(845, 900));
		
//		ArrayList<StoreVO> __list = storeDAO.selectStoresByStoreAdr("동명동"); // 198
//		ArrayList<StoreVO> __list = storeDAO.selectStoresByStoreAdr("양림동"); // 153
//		ArrayList<StoreVO> __list = storeDAO.selectStoresByStoreAdr("각화동"); // 81
//		ArrayList<StoreVO> __list = storeDAO.selectStoresByStoreAdr("계림동"); // 241
//		ArrayList<StoreVO> __list = storeDAO.selectStoresByStoreAdr("고룡동"); // 1
//		ArrayList<StoreVO> __list = storeDAO.selectStoresByStoreAdr("광산동"); // 85
//		ArrayList<StoreVO> __list = storeDAO.selectStoresByStoreAdr("구동"); // 
//		ArrayList<StoreVO> __list = storeDAO.selectStoresByStoreAdr("궁동"); // 
//		ArrayList<StoreVO> __list = storeDAO.selectStoresByStoreAdr("금곡동"); // 
//		ArrayList<StoreVO> __list = storeDAO.selectStoresByStoreAdr("금남로1가"); // 
//		ArrayList<StoreVO> __list = storeDAO.selectStoresByStoreAdr("금남로2가"); // 
//		ArrayList<StoreVO> __list = storeDAO.selectStoresByStoreAdr("금남로3가"); // 
//		ArrayList<StoreVO> __list = storeDAO.selectStoresByStoreAdr("금남로4가"); // 
//		ArrayList<StoreVO> __list = storeDAO.selectStoresByStoreAdr("금남로5가"); // 
//		ArrayList<StoreVO> __list = storeDAO.selectStoresByStoreAdr("금동"); // 
//		ArrayList<StoreVO> __list = storeDAO.selectStoresByStoreAdr("금호동"); // 
//		ArrayList<StoreVO> __list = storeDAO.selectStoresByStoreAdr("광천동"); // 355
//		ArrayList<StoreVO> __list = storeDAO.selectStoresByStoreAdr("남동"); //
//		ArrayList<StoreVO> __list = storeDAO.selectStoresByStoreAdr("내남동"); //
//		ArrayList<StoreVO> __list = storeDAO.selectStoresByStoreAdr("내방동"); //
//		ArrayList<StoreVO> __list = storeDAO.selectStoresByStoreAdr("노대동"); //
//		ArrayList<StoreVO> __list = storeDAO.selectStoresByStoreAdr("농성동"); //
//		ArrayList<StoreVO> __list = storeDAO.selectStoresByStoreAdr("누문동"); //
//		ArrayList<StoreVO> __list = storeDAO.selectStoresByStoreAdr("대산동"); //
//		__list.addAll(storeDAO.selectStoresByStoreAdr("대의동")); //
//		__list.addAll(storeDAO.selectStoresByStoreAdr("대인동")); //
//		__list.addAll(storeDAO.selectStoresByStoreAdr("대촌동")); //
//		__list.addAll(storeDAO.selectStoresByStoreAdr("덕남동")); //
//		__list.addAll(storeDAO.selectStoresByStoreAdr("덕흥동")); //
//		__list.addAll(storeDAO.selectStoresByStoreAdr("도덕동")); //
//		__list.addAll(storeDAO.selectStoresByStoreAdr("도산동")); //
//		__list.addAll(storeDAO.selectStoresByStoreAdr("도천동")); //
//		__list.addAll(storeDAO.selectStoresByStoreAdr("동림동")); //
//		__list.addAll(storeDAO.selectStoresByStoreAdr("동산동")); //
		
		ArrayList<StoreVO> __list = storeDAO.selectStoresByStoreAdr("청풍동"); //
		__list.addAll(storeDAO.selectStoresByStoreAdr("충장로1가")); //
		__list.addAll(storeDAO.selectStoresByStoreAdr("충장로2가")); //
		__list.addAll(storeDAO.selectStoresByStoreAdr("충장로3가")); //
		__list.addAll(storeDAO.selectStoresByStoreAdr("충장로4가")); //
		__list.addAll(storeDAO.selectStoresByStoreAdr("충장로5가")); //
		__list.addAll(storeDAO.selectStoresByStoreAdr("충효동")); //
		__list.addAll(storeDAO.selectStoresByStoreAdr("지평동")); //
		__list.addAll(storeDAO.selectStoresByStoreAdr("태령동")); //
		__list.addAll(storeDAO.selectStoresByStoreAdr("풍암동")); //
		__list.addAll(storeDAO.selectStoresByStoreAdr("풍향동")); //
		__list.addAll(storeDAO.selectStoresByStoreAdr("하남동")); //
		__list.addAll(storeDAO.selectStoresByStoreAdr("하산동")); //
		__list.addAll(storeDAO.selectStoresByStoreAdr("학동")); //
		__list.addAll(storeDAO.selectStoresByStoreAdr("행암동")); //
		__list.addAll(storeDAO.selectStoresByStoreAdr("호남동")); //
		__list.addAll(storeDAO.selectStoresByStoreAdr("화암동")); //
		__list.addAll(storeDAO.selectStoresByStoreAdr("화장동")); //
		__list.addAll(storeDAO.selectStoresByStoreAdr("화정동")); //
		__list.addAll(storeDAO.selectStoresByStoreAdr("황금동")); //
		__list.addAll(storeDAO.selectStoresByStoreAdr("황룡동")); //
		__list.addAll(storeDAO.selectStoresByStoreAdr("효령동")); //
		__list.addAll(storeDAO.selectStoresByStoreAdr("흑석동")); //
		
		ArrayList<StoreVO> _list = new ArrayList<StoreVO>(__list.subList(0, __list.size()));
		
		
		//ArrayList<StoreVO> _list = new ArrayList<StoreVO>(__list.subList(235, __list.size()));
		logger.info("총 사이즈 : {}", __list.size());
		
		for(int i = 0; i < _list.size(); i += 5) {
			logger.info("{}", i);
			logger.info("{}", i + 5);
			
			ArrayList<StoreVO> list = new ArrayList<StoreVO>(_list.subList(i, i + 5));
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
			try {
				Thread.sleep(10000);
				Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe /t");
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		long endTime = System.currentTimeMillis();
		long diff = (endTime - startTime) / 1000;
		logger.info("걸린 시간 : {}", diff);
		return "crawl_test";
	}
	
}
