package com.severett.restaurants.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.severett.restaurants.model.Guest;
import com.severett.restaurants.model.Reservation;
import com.severett.restaurants.model.RestaurantTable;
import com.severett.restaurants.repositories.ReservationRepository;
import com.severett.restaurants.util.RestaurantConstants;

@Service
public class ReservationServiceImpl implements ReservationService {

    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public Reservation createReservation(RestaurantTable restaurantTable, Guest guest, String startTimeString) {
        if (Strings.isNotBlank(startTimeString)) {
            try {
                startTimeString = startTimeString.trim();
                logger.info("Request for available hours for date {}", startTimeString);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(RestaurantConstants.TIME_FORMAT);
                LocalDateTime ldt = LocalDateTime.parse(startTimeString, formatter);
                Date startTimeDate = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startTimeDate);
                calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + 1);
                Date endTimeDate = calendar.getTime();
                Reservation reservation = new Reservation(restaurantTable, guest, startTimeDate, endTimeDate);
                Date currentTime = new Date();
                reservation.setCreatedDate(currentTime);
                reservation.setUpdatedDate(currentTime);
                return saveEntity(reservation);
            } catch (DateTimeParseException dtpe) {
                logger.error("Error parsing time: " + dtpe.getMessage());
                return null;
            }
        } else {
            logger.warn("No time provided - returning null");
            return null;
        }
    }

    @Override
    public Reservation saveEntity(Reservation entity) {
        return reservationRepository.saveAndFlush(entity);
    }

    @Override
    public List<Reservation> saveEntities(Iterable<Reservation> entities) {
        List<Reservation> savedEntities = reservationRepository.save(entities);
        reservationRepository.flush();
        return savedEntities;
    }
}
