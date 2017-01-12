package com.severett.restaurants.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.severett.restaurants.model.DateHours;
import com.severett.restaurants.repositories.DateHoursRepository;

@Service
public class DateHoursServiceImpl implements DateHoursService {

	private static final Logger logger = LoggerFactory.getLogger(DateHoursService.class);
	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private DateHoursRepository dateHoursRepository;

	@Override
	public DateHours getDateHours(String targetDateString) {
		if (Strings.isNotBlank(targetDateString)) {
			try {
				targetDateString = targetDateString.trim();
				logger.info("Request for available hours for date {}", targetDateString);
				Date targetDate = df.parse(targetDateString);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(targetDate);
				Short dayOfWeek = (short) calendar.get(Calendar.DAY_OF_WEEK);
				return dateHoursRepository.findOne(dayOfWeek);
			} catch (ParseException pe) {
				logger.error("Error parsing date:", pe);
				pe.printStackTrace();
				return null;
			}
		} else {
			logger.warn("No date provided - returning null");
			return null;
		}
	}
}
