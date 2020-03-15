package com.yeollu.getrend.map.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeollu.getrend.map.service.Polygon;
import com.yeollu.getrend.map.vo.PointVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		

		return "home";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public void search(@RequestBody ArrayList<PointVO> points) {
		logger.info("{}", points);
		
		Polygon polygon = new Polygon();
		for(int i = 0; i < points.size(); i++) {
			polygon.addPoint(points.get(i).getxPos(), points.get(i).getyPos());
		}
		
		logger.info("{}", polygon.contains(126.92361744165378, 35.15089373706751));
		logger.info("{}", polygon.contains(126.92458323897296, 35.150849274005616));
		logger.info("{}", polygon.contains(126.92439619739558, 35.15136293304344));
		logger.info("{}", polygon.contains(126.92358400412023, 35.15144354600264));
		
		
		logger.info("{}", polygon.contains(126.92021644758223, 35.14976484441918));
		logger.info("{}", polygon.contains(126.9289520660696, 35.14972521339559));
		logger.info("{}", polygon.contains(126.92898245951886, 35.152636626341746));
		logger.info("{}", polygon.contains(126.92014784531796, 35.152586058093476));
		
		
	}
	
}
