package com.yeollu.getrend.store.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Class 	: HomeController.java
 * @Package	: com.yeollu.getrend.store.controller
 * @Project : GeTrend
 * @Author	: 박민열
 * @Since	: 2020. 3. 12.
 * @Version	: 1.0
 * @Desc	: 메인 페이지로 이동한다.
 */
@Controller
public class HomeController {

	/**
	 * Fields
	 */
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * @Method	: home
	 * @Return	: String
	 * @Author	: 박민열
	 * @Since	: 2020. 3. 12.
	 * @Version	: 1.0
	 * @Desc	: home.jsp로 페이지를 전환한다.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
}
