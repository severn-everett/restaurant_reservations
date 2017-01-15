package com.severett.restaurants.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.severett.restaurants.model.Guest;
import com.severett.restaurants.model.Reservation;
import com.severett.restaurants.model.RestaurantTable;
import com.severett.restaurants.repositories.ReservationRepository;
import com.severett.restaurants.util.RestaurantConstants;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public Reservation createReservation(RestaurantTable restaurantTable, Guest guest, String startTimeString) {
        try {
            DateFormat formatter = new SimpleDateFormat(RestaurantConstants.TIME_FORMAT);
            Date startTimeDate = formatter.parse(startTimeString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startTimeDate);
            calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + 1);
            Date endTimeDate = calendar.getTime();
            Reservation reservation = new Reservation(restaurantTable, guest, startTimeDate, endTimeDate);
            return reservationRepository.saveAndFlush(reservation);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
