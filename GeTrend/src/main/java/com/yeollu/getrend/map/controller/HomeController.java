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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeollu.getrend.map.dao.StoresDAO;
import com.yeollu.getrend.map.service.Point;
import com.yeollu.getrend.map.service.Polygon;
import com.yeollu.getrend.map.vo.StoresVO;
import com.yeollu.getrend.service.webcrawler.InstagramSeleniumCrawler;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private StoresDAO storesDAO;
	
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
	public void search(@RequestBody ArrayList<Point> points) {
		long beforeTime = System.currentTimeMillis();
		logger.info("{}", points);
		ArrayList<StoresVO> list = storesDAO.selectAllStores();
		logger.info("{}", list.size());
		
		Polygon polygon = new Polygon();
		for(int i = 0; i < points.size(); i++) {
			polygon.addPoint(points.get(i));
		}
		
		ArrayList<StoresVO> selectedList = new ArrayList<StoresVO>();
		
		for(int i = 0 ; i < list.size(); i++) {
			if(polygon.isContains(list.get(i).getX(), list.get(i).getY())) {
				selectedList.add(list.get(i));
			}
		}
		
		logger.info("{}", selectedList.size());
		

		for(int i = 0; i < selectedList.size(); i++) {
			logger.info("{}", selectedList.get(i).getStore_name());
			InstagramSeleniumCrawler crawler = new InstagramSeleniumCrawler();
			crawler.crawl(selectedList.get(i).getStore_name());
		}
//		crawler.crawl(selectedList.get(0).getStore_name());
//		
//		InstagramSeleniumCrawler crawler2 = new InstagramSeleniumCrawler();
//		crawler2.crawl(selectedList.get(1).getStore_name());
		
		long afterTime = System.currentTimeMillis();
		
		long secDiffTime = (afterTime - beforeTime) / 1000;
		logger.info("{}", secDiffTime);
		
//		logger.info("{}", polygon.isContains(126.92361744165378, 35.15089373706751));
//		logger.info("{}", polygon.isContains(126.92458323897296, 35.150849274005616));
//		logger.info("{}", polygon.isContains(126.92439619739558, 35.15136293304344));
//		logger.info("{}", polygon.isContains(126.92358400412023, 35.15144354600264));
//		
//		
//		logger.info("{}", polygon.isContains(126.9216865520651, 35.15024352473057));
//		logger.info("{}", polygon.isContains(126.92620795194445, 35.15030945788169));
//		logger.info("{}", polygon.isContains(126.9263601320809, 35.15193199974595));
//		logger.info("{}", polygon.isContains(126.92173970669182, 35.15203726682015));
		
		
		
	}
	
}
