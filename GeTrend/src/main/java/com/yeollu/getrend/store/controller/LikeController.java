package com.yeollu.getrend.store.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeollu.getrend.store.dao.LikeDAO;
import com.yeollu.getrend.store.vo.LikeVO;

/**
 * @Class 	: LikeController.java
 * @Package	: com.yeollu.getrend.store.controller
 * @Project : GeTrend
 * @Author	: 조은채
 * @Since	: 2020. 4. 11.
 * @Version	: 1.0
 * @Desc	: 가게 좋아요 기능을 제어한다.
 */
@Controller
@RequestMapping(value = "/likes")
public class LikeController {
	
	/**
	 * Fields
	 */
	private static final Logger logger = LoggerFactory.getLogger(LikeController.class);
	@Autowired
	private LikeDAO likeDAO;
		
	/**
	 * @Method	: likeInsert
	 * @Return	: void
	 * @Author	: 조은채
	 * @Since	: 2020. 4. 11.
	 * @Version	: 1.0
	 * @Desc	: likes 테이블에 데이터를 추가한다.
	 * @param like
	 * @param session
	 */
	@RequestMapping(value = "/likeInsert", method = RequestMethod.POST)
	@ResponseBody
	public void likeInsert(LikeVO like, HttpSession session) {
		like.setUser_email((String)session.getAttribute("loginemail"));
		logger.info("likeInsert {}",like);
		
		if(likeDAO.isExistedLike(like)) {
			logger.info("이미 추가됨");
			return;
		} else {
			int cnt = likeDAO.likeInsert(like);
			logger.info("likeInsert cnt {}",cnt);
		}
	}
	
	/**
	 * @Method	: likeDelete
	 * @Return	: void
	 * @Author	: 조은채
	 * @Since	: 2020. 4. 11.
	 * @Version	: 1.0
	 * @Desc	: likes 테이블에 데이터를 제거한다.
	 * @param like
	 * @param session
	 */
	@RequestMapping(value = "/likeDelete", method = RequestMethod.POST)
	@ResponseBody
	public void likeDelete(LikeVO like,HttpSession session) {
		logger.info("likeDelete");
		like.setUser_email((String)session.getAttribute("loginemail"));
		int cnt = likeDAO.likeDelete(like);
		logger.info("likeDelete cnt {}",cnt);
	}
}
