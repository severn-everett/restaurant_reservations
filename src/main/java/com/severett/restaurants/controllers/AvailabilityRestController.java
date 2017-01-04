package com.severett.restaurants.controllers;

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
	
	@RequestMapping("/hours")
	public Map<String, Integer> getAvailableHours(@RequestParam(value="date") String targetDateString) {
		Map<String, Integer> hoursMap = new HashMap<>();
		DateHours dateHours = dateHoursService.getDateHours(targetDateString);
		if (dateHours != null) {
			hoursMap.put(BEGIN_HOUR, dateHours.getStartHour());
			hoursMap.put(END_HOUR, dateHours.getEndHour());
		}
		return hoursMap;
	}	
}
