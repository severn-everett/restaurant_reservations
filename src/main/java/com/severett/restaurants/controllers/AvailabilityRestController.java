package com.severett.restaurants.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.severett.restaurants.model.DateHours;
import com.severett.restaurants.services.DateHoursService;

@RestController(value="/availability")
public class AvailabilityRestController {
	
	private static final String BEGIN_HOUR = "beginHour";
	private static final String END_HOUR = "endHour";
	
	@Autowired
	DateHoursService dateHoursService;
	
	@RequestMapping("/tables")
	public Map<String, Object> getOpenTables() {
		Map<String, Object> tablesMap = new HashMap<>();
		
		return tablesMap;
	}
	
	@RequestMapping("/hours")
	public Map<String, Integer> getAvailableHours(@RequestParam(value="date") String dateString) {
		Map<String, Integer> hoursMap = new HashMap<>();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		try {
			Date targetDate = df.parse(dateString);
			DateHours dateHours = dateHoursService.getDateHours(targetDate);
			hoursMap.put(BEGIN_HOUR, dateHours.getStartHour());
			hoursMap.put(END_HOUR, dateHours.getEndHour());
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return hoursMap;
	}
	
}
