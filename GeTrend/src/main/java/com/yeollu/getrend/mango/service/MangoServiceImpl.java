package com.yeollu.getrend.mango.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeollu.getrend.crawler.CrawlerExecutor;
import com.yeollu.getrend.crawler.CrawlerExecutorForMango;
import com.yeollu.getrend.mango.dao.MangoDayDAO;
import com.yeollu.getrend.mango.dao.MangoStoreDAO;
import com.yeollu.getrend.mango.dao.MangoTimeDAO;
import com.yeollu.getrend.mango.vo.MangoDayVO;
import com.yeollu.getrend.mango.vo.MangoStoreVO;
import com.yeollu.getrend.mango.vo.MangoTimeVO;
import com.yeollu.getrend.store.dao.InstaLocationDAO;
import com.yeollu.getrend.store.dao.ScoreDAO;
import com.yeollu.getrend.store.dao.StoreDAO;
import com.yeollu.getrend.store.util.preprocess.core.DayOfTheWeekCategorizer;
import com.yeollu.getrend.store.util.preprocess.core.TimeCategorizer;
import com.yeollu.getrend.store.vo.InstaStoreVO;
import com.yeollu.getrend.store.vo.StoreVO;

@Service
public class MangoServiceImpl implements MangoService {
	
	private static final Logger logger = LoggerFactory.getLogger(MangoServiceImpl.class);
	
	@Autowired
	private StoreDAO storeDAO;
	
	@Autowired
	private MangoStoreDAO mangoStoreDAO;
	
	@Autowired
	private MangoDayDAO mangoDayDAO;
	
	@Autowired
	private MangoTimeDAO mangoTimeDAO;

	@Override
	public ArrayList<MangoStoreVO> updateMangoStores() {
		logger.info("망고 스토어 업데이트 시작");
		
		ArrayList<StoreVO> storeList = storeDAO.selectAllStores();
		
		storeList =  new ArrayList<StoreVO> (storeList.subList(0, 5));
		
		ArrayList<CrawlerExecutorForMango> crawlerExecutorForMangoList = new ArrayList<CrawlerExecutorForMango>();
		for(StoreVO store : storeList) {
			CrawlerExecutorForMango crawlerExecutorForMango = new CrawlerExecutorForMango();
			crawlerExecutorForMango.setStore(store);
			new Thread(crawlerExecutorForMango, "crawling : " + store.getStore_name()).start();
			crawlerExecutorForMangoList.add(crawlerExecutorForMango);
		}
		
		ArrayList<MangoStoreVO> mangoStoreList = new ArrayList<MangoStoreVO>();
		for(CrawlerExecutorForMango crawlerExecutorForMango : crawlerExecutorForMangoList) {
			mangoStoreList.add(crawlerExecutorForMango.getMangoStore());
		}
		CrawlerExecutorForMango.killChromeDriver();
		logger.info("{}", mangoStoreList);
		
		for(MangoStoreVO mangoStore : mangoStoreList) {
			int cnt = mangoStoreDAO.updateMangoStore(mangoStore);
			if(cnt > 0) {
				logger.info("망고 스토어 업데이트 성공");
			} else {
				logger.info("망고 스토어 업데이트 실패");
			}
		}
		logger.info("망고 스토어 업데이트 종료");
		return mangoStoreList;
	}

	@Override
	public void updateMangoDays(ArrayList<MangoStoreVO> mangoStoreList) {
		logger.info("망고 데이 업데이트 시작");
		for(MangoStoreVO mangoStore : mangoStoreList) {
			DayOfTheWeekCategorizer c = new DayOfTheWeekCategorizer();
			c.categorize(mangoStore.getMango_time());
			HashMap<String, String> resultMap = c.getResultMap();
			MangoDayVO mangoDay = new MangoDayVO();
			mangoDay.setStore_no(mangoStore.getStore_no());
			mangoDay.setMango_sun(resultMap.get("일"));
			mangoDay.setMango_mon(resultMap.get("월"));
			mangoDay.setMango_tue(resultMap.get("화"));
			mangoDay.setMango_wed(resultMap.get("수"));
			mangoDay.setMango_thu(resultMap.get("목"));
			mangoDay.setMango_fri(resultMap.get("금"));
			mangoDay.setMango_sat(resultMap.get("토"));
			int cnt = mangoDayDAO.updateMangoDay(mangoDay);
			if(cnt > 0) {
				logger.info("망고 데이 업데이트 성공");
			} else {
				logger.info("망고 데이 업데이트 실패");
			}
		}
		logger.info("망고 데이 업데이트 종료");
	}
	
	@Override
	public void updateMangoTimes(ArrayList<MangoStoreVO> mangoStoreList) {
		logger.info("망고 타임 업데이트 시작");
		for(MangoStoreVO mangoStore : mangoStoreList) {
			TimeCategorizer c = new TimeCategorizer();
			c.categorize(mangoStore.getMango_time());
			HashMap<String, String> resultMap = c.getResultMap();
			MangoTimeVO mangoTime = new MangoTimeVO();
			mangoTime.setStore_no(mangoStore.getStore_no());
			mangoTime.setMango_start(resultMap.get("시작시간"));
			mangoTime.setMango_end(resultMap.get("종료시간"));
			int cnt = mangoTimeDAO.updateMangoTime(mangoTime);
			if(cnt > 0) {
				logger.info("망고 타임 업데이트 성공");
			} else {
				logger.info("망고 타임 업데이트 실패");
			}
		}
		logger.info("망고 타임 업데이트 종료");
	}
	
}
