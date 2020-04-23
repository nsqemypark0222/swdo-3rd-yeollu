package com.yeollu.getrend.store.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeollu.getrend.crawler.CrawlerExecutor;
import com.yeollu.getrend.store.dao.InstaImageDAO;
import com.yeollu.getrend.store.dao.InstaLocationDAO;
import com.yeollu.getrend.store.dao.MangoStoreInfoDAO;
import com.yeollu.getrend.store.dao.ScoreDAO;
import com.yeollu.getrend.store.dao.StoreDAO;
import com.yeollu.getrend.store.util.preprocess.core.QueryStringSender;
import com.yeollu.getrend.store.vo.InstaImageVO;
import com.yeollu.getrend.store.vo.InstaLocationVO;
import com.yeollu.getrend.store.vo.InstaStoreInfoVO;
import com.yeollu.getrend.store.vo.InstaStoreVO;
import com.yeollu.getrend.store.vo.MangoStoreInfoVO;
import com.yeollu.getrend.store.vo.ScoreVO;
import com.yeollu.getrend.store.vo.StoreVO;

@Service
public class StoreServiceImpl implements StoreService {
	private static final Logger logger = LoggerFactory.getLogger(StoreServiceImpl.class);
	
	private static final int MAX_SIZE_OF_LIST = 5;
	
	@Autowired
	private StoreDAO storeDAO;

	@Autowired
	private InstaLocationDAO instaLocationDAO;
	
	@Autowired
	private MangoStoreInfoDAO mangoStoreInfoDAO;
	
	@Autowired
	private ScoreDAO scoreDAO;
	
	@Autowired
	private InstaImageDAO instaImageDAO;

	@Override
	public ArrayList<InstaStoreVO> generateInstaStoreList(ArrayList<StoreVO> storeList) {
		ArrayList<InstaStoreVO> instaStoreList = new ArrayList<InstaStoreVO>();
		
		for (StoreVO store : storeList) {
			InstaLocationVO instaLocation = instaLocationDAO.selectLocationByStoreNo(store.getStore_no());
			if(instaLocation == null) {
				String location_id = QueryStringSender.send(store);
				if (location_id == null || location_id.equals("")) {
				} else {
					if(!instaLocationDAO.isExistedInstaLocation(location_id)) {
						InstaLocationVO _instaLocation = new InstaLocationVO();
						_instaLocation.setLocation_id(location_id);
						_instaLocation.setStore_no(store.getStore_no());
						instaLocationDAO.insertInstaLocation(_instaLocation);
					}

					InstaStoreVO instaStore = storeDAO.selectInstaStore(store.getStore_no());
					if (instaStore != null) {
						instaStore.setLocation_id(location_id);
						instaStoreList.add(instaStore);
					}
				}
			} else {
				InstaStoreVO instaStore = storeDAO.selectInstaStore(instaLocation.getStore_no());
				if (instaStore != null) {
					instaStore.setLocation_id(instaLocation.getLocation_id());
					instaStoreList.add(instaStore);
				}
			}
			
		}
		return instaStoreList;
	}
	
	@Override
	public ArrayList<MangoStoreInfoVO> generateMangoStoreInfoList(ArrayList<InstaStoreVO> instaStoreList, ArrayList<String> opentimeValues) {
		ArrayList<MangoStoreInfoVO> mangoStoreInfoList = new ArrayList<MangoStoreInfoVO>();
		for(InstaStoreVO instaStore : instaStoreList) {
			MangoStoreInfoVO mangoStoreInfo = new MangoStoreInfoVO();
			if(opentimeValues != null) {
				mangoStoreInfo = mangoStoreInfoDAO.selectMangoStoreInfoByStoreNoAndDays(instaStore.getStore_no(), opentimeValues);
			} else {
				mangoStoreInfo = mangoStoreInfoDAO.selectMangoStoreInfoByStoreNo(instaStore.getStore_no());
			}
			mangoStoreInfoList.add(mangoStoreInfo);
		}
		return mangoStoreInfoList;
	}
	
	@Override
	public ArrayList<ScoreVO> generateScoreList(ArrayList<InstaStoreVO> instaStoreList) {
		ArrayList<ScoreVO> scoreList = new ArrayList<ScoreVO>();
		for(InstaStoreVO instaStore : instaStoreList) {
			ScoreVO score = scoreDAO.selectScoreByStoreNo(instaStore.getStore_no());
			
			if(score == null) {
				score = new ScoreVO();
				score.setStore_no(instaStore.getStore_no());
				score.setSum_of_like(0);
				score.setSum_of_star(0.0);
			}
			scoreList.add(score);
		}
		
		Collections.sort(scoreList, new Comparator<ScoreVO>() {
			public int compare(ScoreVO score1, ScoreVO score2) {
				if((score1.getSum_of_like() * 100 + score1.getSum_of_like()) < (score2.getSum_of_like() * 100 + score2.getSum_of_like())) {
					return 1;
				} else {
					return -1;
				}
				
			};
		});
		return scoreList;
	}
	
	@Override
	public ArrayList<InstaStoreVO> sortInstaStoreList(ArrayList<InstaStoreVO> instaStoreList, ArrayList<ScoreVO> scoreList) {
		ArrayList<InstaStoreVO> _instaStoreList = new ArrayList<InstaStoreVO>();
		for(int i = 0; i < scoreList.size(); i++) {
			for(int j = 0; j < instaStoreList.size(); j++) {
				if(scoreList.get(i).getStore_no().equals(instaStoreList.get(j).getStore_no())) {
					_instaStoreList.add(instaStoreList.get(j));
					break;
				}
			}
		}
		if(_instaStoreList.size() > MAX_SIZE_OF_LIST) {
			instaStoreList = new ArrayList<InstaStoreVO> (_instaStoreList.subList(0, MAX_SIZE_OF_LIST));
		} else {
			instaStoreList = _instaStoreList;
		}
		return instaStoreList;
	}
	
	@Override
	public ArrayList<InstaImageVO> requestCrawling(ArrayList<InstaStoreVO> instaStoreList) {
		ArrayList<InstaImageVO> instaImageList = new ArrayList<InstaImageVO>();
		ArrayList<CrawlerExecutor> crawlerExecutorList = new ArrayList<CrawlerExecutor>();
		for (InstaStoreVO instaStore : instaStoreList) {
			
			CrawlerExecutor crawlerExecutor = new CrawlerExecutor();
			crawlerExecutor.setStore_no(instaStore.getStore_no());
			crawlerExecutor.setLocation_id(instaStore.getLocation_id());
			
			// 이미 존재
			if(instaImageDAO.isExistedInstaImage(instaStore.getStore_no())) {
				crawlerExecutor.setIsExisted(true);
				// 존재하지만 업데이트 필요
				if(instaImageDAO.isRequiredUpdateInstaImage(instaStore.getStore_no())) {
					crawlerExecutor.setIsRequiredUpdate(true);
				} else {
					crawlerExecutor.setIsRequiredUpdate(false);
				}
			} else {
				crawlerExecutor.setIsExisted(false);
			}
			new Thread(crawlerExecutor, "crawling :  " + instaStore.getLocation_id()).start();
			crawlerExecutorList.add(crawlerExecutor);
		}
		
		for (CrawlerExecutor crawlerExecutor : crawlerExecutorList) {
			if(crawlerExecutor.getIsExisted()) {
				if(crawlerExecutor.getIsRequiredUpdate()) {
					int cnt = instaImageDAO.deleteInstaImage(crawlerExecutor.getStore_no());
					if(cnt > 0) {
						logger.info("업데이트를 위한 삭제 성공");
					} else {
						logger.info("업데이트를 위한 삭제 실패");
					}
					for(InstaImageVO instaImage : crawlerExecutor.getInstaImageList()) {
						cnt = 0;
						cnt = instaImageDAO.insertInstaImage(instaImage);
						if(cnt > 0) {
							logger.info("업데이트 성공");
						} else {
							logger.info("업데이트 실패");
						}
					}
				}
			} else {
				for(InstaImageVO instaImage : crawlerExecutor.getInstaImageList()) {
					int cnt = instaImageDAO.insertInstaImage(instaImage);
					if(cnt > 0) {
						logger.info("추가 성공");
					} else {
						logger.info("추가 실패");
					}
				}
			}
			instaImageList.addAll(instaImageDAO.selectInstaImageByStoreNo(crawlerExecutor.getStore_no()));
		}
		CrawlerExecutor.killChromeDriver();
		return instaImageList;
	}
	
	@Override
	public ArrayList<InstaStoreInfoVO> generateInstaStoreInfoList(ArrayList<InstaStoreVO> instaStoreList,
			ArrayList<MangoStoreInfoVO> mangoStoreInfoList, ArrayList<ScoreVO> scoreList,
			ArrayList<InstaImageVO> instaImageList) {
		ArrayList<InstaStoreInfoVO> instaStoreInfoList = new ArrayList<InstaStoreInfoVO>();
		try {
			for (int i = 0; i < instaStoreList.size(); i++) {
				InstaStoreInfoVO instaStoreInfo = new InstaStoreInfoVO();
				instaStoreInfo.setInstaStore(instaStoreList.get(i));
				instaStoreInfo.setMangoStoreInfo(mangoStoreInfoList.get(i));
				instaStoreInfo.setScore(scoreList.get(i));
				
				ArrayList<InstaImageVO> _instaImageList = new ArrayList<InstaImageVO>();
				for(InstaImageVO instaImage : instaImageList) {
					if(instaStoreInfo.getInstaStore().getStore_no().equals(instaImage.getStore_no())) {
						_instaImageList.add(instaImage);
					}
				}

				if (_instaImageList.size() > 0) {
					instaStoreInfo.setInstaImageList(_instaImageList);
				} else {
					instaStoreInfo.setInstaImageList(null);
				}
				instaStoreInfoList.add(instaStoreInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instaStoreInfoList;
	}
	
	@Override
	public ArrayList<InstaStoreInfoVO> sortInstaStoreInfoList(ArrayList<InstaStoreInfoVO> instaStoreInfoList) {
		for(InstaStoreInfoVO instaStoreInfo : instaStoreInfoList) {
			ScoreVO score = instaStoreInfo.getScore();
			
			int sum = 0;
			if(instaStoreInfo.getInstaImageList() != null) {
				for(InstaImageVO instaImage : instaStoreInfo.getInstaImageList()) {
					sum += instaImage.getImage_like();
				}
			}
			score.setSum_of_insta_like(sum);
			instaStoreInfo.setScore(score);
		}
		
		Collections.sort(instaStoreInfoList, new Comparator<InstaStoreInfoVO>() {
			public int compare(InstaStoreInfoVO instaScoreInfo1, InstaStoreInfoVO instaScoreInfo2) {
				if(instaScoreInfo1.getScore().getSum_of_insta_like() < instaScoreInfo2.getScore().getSum_of_insta_like()) {
					return 1;
				} else {
					return -1;
				}
			};
		});
		return instaStoreInfoList;
	}
	
	@Override
	public InstaStoreInfoVO generateIStoreInfo(String store_no) {
		InstaStoreVO instaStore = storeDAO.selectInstaStore(store_no);
		
		ArrayList<InstaImageVO> instaImageList = instaImageDAO.selectInstaImageByStoreNo(store_no);
		
		MangoStoreInfoVO mangoStoreInfo = mangoStoreInfoDAO.selectMangoStoreInfoByStoreNo(store_no);
		
		ScoreVO score = scoreDAO.selectScoreByStoreNo(store_no);
		
		InstaStoreInfoVO instaStoreInfo = new InstaStoreInfoVO();
		instaStoreInfo.setInstaStore(instaStore);
		instaStoreInfo.setInstaImageList(instaImageList);
		instaStoreInfo.setMangoStoreInfo(mangoStoreInfo);
		instaStoreInfo.setScore(score);
		return instaStoreInfo;
	}
	
	@Override
	public InstaStoreInfoVO updateIStoreInfo(String store_no) {
		
		InstaStoreVO instaStore = storeDAO.selectInstaStore(store_no);
		CrawlerExecutor crawlerExecutor = new CrawlerExecutor();
		crawlerExecutor.setStore_no(instaStore.getStore_no());
		crawlerExecutor.setLocation_id(instaStore.getLocation_id());
		crawlerExecutor.setIsExisted(true);
		crawlerExecutor.setIsRequiredUpdate(true);
		new Thread(crawlerExecutor, "crawling :  " + instaStore.getLocation_id()).start();
		
		int cnt = instaImageDAO.deleteInstaImage(crawlerExecutor.getStore_no());
		if(cnt > 0) {
			logger.info("업데이트를 위한 삭제 성공");
		} else {
			logger.info("업데이트를 위한 삭제 실패");
		}
		
		for(InstaImageVO instaImage : crawlerExecutor.getInstaImageList()) {
			cnt = instaImageDAO.insertInstaImage(instaImage);
			if(cnt > 0) {
				logger.info("업데이트 성공");
			} else {
				logger.info("업데이트 실패");
			}
		}
		CrawlerExecutor.killChromeDriver();
		
		return generateIStoreInfo(store_no);
	}
}
