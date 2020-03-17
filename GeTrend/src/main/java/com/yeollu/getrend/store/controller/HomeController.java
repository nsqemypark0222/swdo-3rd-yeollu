package com.yeollu.getrend.store.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeollu.getrend.map.core.Point;
import com.yeollu.getrend.map.core.Polygon;
import com.yeollu.getrend.store.dao.StoreDAO;
import com.yeollu.getrend.store.vo.StoreVO;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private StoreDAO storeDAO;
	
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
		logger.info("search");
		long startTime = System.currentTimeMillis();
		
		logger.info("{}", points);
		ArrayList<StoreVO> list = storeDAO.selectAllStores();
		logger.info("{}", list.size());
		
		Polygon polygon = new Polygon();
		for(int i = 0; i < points.size(); i++) {
			polygon.addPoint(points.get(i));
		}
		
		ArrayList<StoreVO> selectedList = new ArrayList<StoreVO>();
		
		for(int i = 0 ; i < list.size(); i++) {
			if(polygon.isContains(list.get(i).getStore_x(), list.get(i).getStore_y())) {
				selectedList.add(list.get(i));
			}
		}
		logger.info("{}", selectedList.size());
		
		for(int i = 0; i < selectedList.size(); i++) {
			logger.info("{}", selectedList.get(i).getStore_name());

		}
		
		long endTime = System.currentTimeMillis();
		long diff = (endTime - startTime) / 1000;
		logger.info("걸린 시간 : {}", diff);
		
	}
	
}
