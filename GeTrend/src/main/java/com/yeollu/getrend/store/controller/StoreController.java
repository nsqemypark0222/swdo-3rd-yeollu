package com.yeollu.getrend.store.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeollu.getrend.store.dao.InstaImageDAO;
import com.yeollu.getrend.store.dao.InstaReplyDAO;
import com.yeollu.getrend.store.dao.LikeDAO;
import com.yeollu.getrend.store.dao.MangoStoreInfoDAO;
import com.yeollu.getrend.store.dao.ScoreDAO;
import com.yeollu.getrend.store.dao.StoreDAO;
import com.yeollu.getrend.store.service.StoreServiceImpl;
import com.yeollu.getrend.store.vo.InstaReplyVO;
import com.yeollu.getrend.store.vo.InstaStoreInfoVO;
import com.yeollu.getrend.store.vo.LikeVO;

/**
 * @Class 	: StoreController.java
 * @Package	: com.yeollu.getrend.store.controller
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 3. 12.
 * @Version	: 1.0
 * @Desc	: 가게 정보 관련 작업을 제어한다.
 */
@Controller
@RequestMapping(value = "/stores")
public class StoreController {
	
	/**
	 * Fields
	 */
	private static final Logger logger = LoggerFactory.getLogger(StoreController.class);
	@Autowired
	private InstaReplyDAO replyDAO;
	@Autowired
	private LikeDAO likeDAO;
	@Autowired
	private ScoreDAO scoreDAO;
	@Autowired
	private StoreServiceImpl storeService;
	
	/**
	 * @Method	: istoreInfo
	 * @Return	: String
	 * @Author	: 오선미, 조은채
	 * @Since	: 2020. 3. 26.
	 * @Version	: 1.0
	 * @Desc	: 가게 정보, 댓글, 좋아요, 별점을 조회해 객체를 생성해 모델에 저장한후 istoreInfo.jsp로 페이지를 전환한다.
	 * @param store_no
	 * @param session
	 * @param model
	 */
	@RequestMapping(value = "/istoreInfo", method = RequestMethod.GET)
	public String istoreInfo(String store_no, HttpSession session, Model model) {
		logger.info("store_no : {}", store_no);
		
		InstaStoreInfoVO instaStoreInfo = storeService.generateInstaStoreInfo(store_no);
		
		model.addAttribute("istore", instaStoreInfo);

        //댓글 리스트
	    String loginemail = (String)session.getAttribute("loginemail");
	    InstaReplyVO reply = new InstaReplyVO();
		reply.setStore_no(store_no);
		reply.setUser_email(loginemail);
		ArrayList<HashMap<String,Object>> replyList = replyDAO.replyList(reply);
		logger.info("replyList {}",replyList);
		model.addAttribute("replyList", replyList);
		int replyCount = replyDAO.replyCountByStoreno(store_no);
		model.addAttribute("replyCount", replyCount);
		
		//좋아요 눌렀나
		LikeVO like =  new LikeVO();
		like.setStore_no(store_no);
		like.setUser_email(loginemail);
		boolean isExistedLike = likeDAO.isExistedLike(like);
		model.addAttribute("isExistedLike", isExistedLike);
		
		//평균 별점
		double scoreAvg = scoreDAO.scoreAvgByStoreNo(store_no);
		model.addAttribute("scoreAvg", scoreAvg);

		return "/stores/istoreInfo";
	}
	
	//댓글 작성 jsp
	/**
	 * @Method	: istoreinfoReply
	 * @Return	: String
	 * @Author	: 오선미, 조은채
	 * @Since	: 2020. 3. 26.
	 * @Version	: 1.0
	 * @Desc	: 댓글 작성을 위해 istoreInfoReply.jsp로 페이지를 전환한다.
	 * @param store_name
	 * @param store_no
	 * @param model
	 */
	@RequestMapping(value="/istoreinfoReply", method=RequestMethod.GET)
	public String istoreinfoReply(String store_name, String store_no, Model model) {
		model.addAttribute("store_name", store_name);
		model.addAttribute("store_no",store_no);
		return "/stores/istoreInfoReply";
	}
	
	//댓글삭제
	/**
	 * @Method	: deleteReply
	 * @Return	: String
	 * @Author	: 오선미, 조은채
	 * @Since	: 2020. 3. 26.
	 * @Version	: 1.0
	 * @Desc	:
	 * @param reply
	 */
	@RequestMapping(value = "/deleteReply", method = RequestMethod.GET)
	public String deleteReply(InstaReplyVO reply) {
		logger.info("{}", reply);
		int cnt = replyDAO.replyRemove(reply);
		if(cnt > 0) {
			logger.info("댓글 삭제 성공");
		} else {
			logger.info("댓글 삭제 실패");
		}
		
		return "redirect:istoreInfo?store_no=" + reply.getStore_no();
	}
	
	/**
	 * @Method	: updateData
	 * @Return	: InstaStoreInfoVO
	 * @Author	: 박민열, 문지연
	 * @Since	: 2020. 4. 05.
	 * @Version	: 1.0
	 * @Desc	: 실시간으로 데이터를 갱신한다.
	 * @param store_no
	 */
	@RequestMapping(value = "/updateData", method = RequestMethod.GET)
	@ResponseBody
	public InstaStoreInfoVO updateData(String store_no) {
		logger.info("실시간 데이터 갱신 시작 : {}", store_no);
		
		InstaStoreInfoVO instaStoreInfo = storeService.updateInstaStoreInfo(store_no);
		
		logger.info("실시간 데이터 갱신 종료");
		return instaStoreInfo;
	}
	
}
