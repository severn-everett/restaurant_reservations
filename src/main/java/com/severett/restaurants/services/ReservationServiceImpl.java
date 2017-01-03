package com.severett.restaurants.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.severett.restaurantes.repositories.ReservationRepository;
import com.severett.restaurants.model.Reservation;
import com.severett.restaurants.model.RestaurantTable;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationRepository reservationRepository;

	@Override
	public Reservation createReservation(RestaurantTable restaurantTable, String startTimeString) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		try {
			Date startTimeDate = formatter.parse(startTimeString);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startTimeDate);
			calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + 1);
			Date endTimeDate = calendar.getTime();
			Reservation reservation = new Reservation(restaurantTable, startTimeDate, endTimeDate);
			return reservationRepository.saveAndFlush(reservation);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}	
}
