package com.severett.restaurants.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.severett.restaurants.model.DateHours;
import com.severett.restaurants.repositories.DateHoursRepository;

@Service
public class DateHoursServiceImpl implements DateHoursService {
	
	@Autowired
	private DateHoursRepository dateHoursRepository;

	@Override
	public DateHours getDateHours(String targetDateString) {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		try {
			Date targetDate = df.parse(targetDateString);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(targetDate);
			Short dayOfWeek = (short) calendar.get(Calendar.DAY_OF_WEEK);
			return dateHoursRepository.findOne(dayOfWeek);
		} catch (ParseException pe) {
			pe.printStackTrace();
			return null;
		}
	}
}
