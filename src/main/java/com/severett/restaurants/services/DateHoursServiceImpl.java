package com.severett.restaurants.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.severett.restaurantes.repositories.DateHoursRepository;
import com.severett.restaurants.model.DateHours;

@Service
public class DateHoursServiceImpl implements DateHoursService {
	
	@Autowired
	private DateHoursRepository dateHoursRepository;

	@Override
	public DateHours getDateHours(Date targetDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(targetDate);
		Short dayOfWeek = (short) calendar.get(Calendar.DAY_OF_WEEK);
		return dateHoursRepository.findOne(dayOfWeek);
	}

}
