package com.severett.restaurants.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.severett.restaurants.model.RestaurantTable;
import com.severett.restaurants.repositories.RestaurantTableRepository;

@Service
public class RestaurantTableServiceImpl implements RestaurantTableService {

	@Autowired
	RestaurantTableRepository restaurantTableRepository;
	
	@Override
	public RestaurantTable findRestaurantTable(Short tableId) {
		return restaurantTableRepository.findOne(tableId);
	}

	@Override
	public RestaurantTable getOpenRestaurantTable(Short partySize, String targetTimeString) {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm");
		try {
			Date startTime = df.parse(targetTimeString);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startTime);
			calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + 1);
			Date endTime = calendar.getTime();
			return restaurantTableRepository.findBySizeAndTimeWindow(partySize, startTime, endTime);
		} catch (ParseException pe) {
			pe.printStackTrace();
			return null;
		}
	}
}
