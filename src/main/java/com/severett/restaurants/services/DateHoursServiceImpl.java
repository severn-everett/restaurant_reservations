package com.severett.restaurants.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.severett.restaurants.util.DateHours;
import com.severett.restaurants.util.RestaurantConstants;

@Service
public class DateHoursServiceImpl implements DateHoursService {

    private static final Logger logger = LoggerFactory.getLogger(DateHoursService.class);

    @Override
    public DateHours getDateHours(String targetDateString) {
        if (Strings.isNotBlank(targetDateString)) {
            try {
                targetDateString = targetDateString.trim();
                logger.info("Request for available hours for date {}", targetDateString);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(RestaurantConstants.DATE_FORMAT);
                LocalDate localDate = LocalDate.parse(targetDateString, formatter);
                return RestaurantConstants.DATE_HOURS_MAP.get(localDate.getDayOfWeek());
            } catch (DateTimeParseException dtpe) {
                logger.error("Error parsing date: " + dtpe.getMessage());
                return null;
            }
        } else {
            logger.warn("No date provided - returning null");
            return null;
        }
    }
}
