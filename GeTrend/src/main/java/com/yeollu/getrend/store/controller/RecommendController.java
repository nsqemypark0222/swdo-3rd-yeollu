package com.yeollu.getrend.store.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yeollu.getrend.store.service.RecommendServiceImpl;
import com.yeollu.getrend.store.vo.InstaStoreInfoVO;
import com.yeollu.getrend.store.vo.RecommendVO;

/**
 * @Class 	: RecommendController.java
 * @Package	: com.yeollu.getrend.store.controller
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 4. 19.
 * @Version	: 1.0
 * @Desc	: 가게 추천 작업을 제어한다.
 */
@Controller
public class RecommendController {
	
	/**
	 * Fields
	 */
	private static final Logger logger = LoggerFactory.getLogger(RecommendController.class);
	@Autowired
	private RecommendServiceImpl recommendService;
	
	/**
	 * @Method	: update
	 * @Return	: void
	 * @Author	: 박민열
	 * @Since	: 2020. 4. 19.
	 * @Version	: 1.0
	 * @Desc	: recommend 테이블을 갱신한다.
	 */
	public void update() {
		ArrayList<RecommendVO> recommendList = recommendService.selectAllRecommendList();
		
		for(RecommendVO recommend : recommendList) {
			recommendService.updateRecommendStores(recommend);
		}
	}
	
	/**
	 * @Method	: recommend
	 * @Return	: String
	 * @Author	: 박민열
	 * @Since	: 2020. 4. 19.
	 * @Version	: 1.0
	 * @Desc	: recommend 테이블을 조회해 추천 가게들의 객체 리스트를 생성해 모델에 저장한 후 searchForm.jsp로 페이지를 전환한다.
	 * @param recommend
	 * @param model
	 */
	@RequestMapping(value = "recommend", method = RequestMethod.GET)
	public String recommend(RecommendVO recommend, Model model) {
		logger.info("추천 시작 : {}, {}", recommend.getStore_adr(), recommend.getStore_cate1());
		long startTime = System.currentTimeMillis();
		
		ArrayList<InstaStoreInfoVO> instaStoreInfoList = recommendService.generateInstaStoreInfo(recommend);
		
		model.addAttribute("istores", instaStoreInfoList);
		
		long endTime = System.currentTimeMillis();
		long diff = (endTime - startTime) / 1000;
		logger.info("걸린 시간 : {}", diff);
		
		logger.info("추천 종료");
		
		return "/searchForm";
	}
}
