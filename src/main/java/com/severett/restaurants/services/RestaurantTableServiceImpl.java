package com.severett.restaurants.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.severett.restaurants.model.RestaurantTable;
import com.severett.restaurants.repositories.RestaurantTableRepository;

@Service
public class RestaurantTableServiceImpl implements RestaurantTableService {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantTableService.class);
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Autowired
    RestaurantTableRepository restaurantTableRepository;

    @Override
    public RestaurantTable findRestaurantTable(Short tableId) {
        return restaurantTableRepository.findOne(tableId);
    }

    @Override
    public RestaurantTable getOpenRestaurantTable(Short partySize, String targetTimeString) {
        try {
            logger.info("Searching for a table of {} at {}", partySize, targetTimeString);
            Date startTime = df.parse(targetTimeString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startTime);
            calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + 1);
            Date endTime = calendar.getTime();
            List<RestaurantTable> tableList = restaurantTableRepository.findBySizeAndTimeWindow(partySize, startTime, endTime);
            if (!tableList.isEmpty()) {
                return tableList.get(0);
            } else {
                return null;
            }
        } catch (ParseException pe) {
            logger.error("Error parsing time '{}': {}", targetTimeString, pe.getMessage());
            pe.printStackTrace();
            return null;
        } catch (Exception e) {
            logger.error("Error getting restaurant table: ", e);
            return null;
        }
    }
}
