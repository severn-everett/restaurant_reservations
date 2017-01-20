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

import com.severett.restaurants.model.RestaurantTable;
import com.severett.restaurants.repositories.RestaurantTableRepository;
import com.severett.restaurants.util.RestaurantConstants;

@Service
public class RestaurantTableServiceImpl implements RestaurantTableService {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantTableService.class);

    @Autowired
    RestaurantTableRepository restaurantTableRepository;

    @Override
    public RestaurantTable findRestaurantTable(Short tableId) {
        return restaurantTableRepository.findOne(tableId);
    }

    @Override
    public RestaurantTable getOpenRestaurantTable(Short partySize, String targetTimeString) {
        if (Strings.isNotBlank(targetTimeString)) {
            try {
                logger.info("Searching for a table of {} at {}", partySize, targetTimeString);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(RestaurantConstants.TIME_FORMAT);
                LocalDateTime ldt = LocalDateTime.parse(targetTimeString, formatter);
                Date startTime = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startTime);
                calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + 1);
                Date endTime = calendar.getTime();
                partySize = (short) (partySize + partySize % 2); // Rounding up to reflect even-numbered table capacities
                List<RestaurantTable> tableList = restaurantTableRepository.findBySizeAndTimeWindow(partySize, startTime, endTime);
                if (!tableList.isEmpty()) {
                    return tableList.get(0);
                } else {
                    return null;
                }
            } catch (DateTimeParseException dtpe) {
                logger.error("Error parsing time '{}': {}", targetTimeString, dtpe.getMessage());
                return null;
            } catch (Exception e) {
                logger.error("Error getting restaurant table: {}", e.getMessage());
                return null;
            }
        } else {
            logger.warn("Target time is blank - returning null...");
            return null;
        }
    }

    @Override
    public RestaurantTable saveEntity(RestaurantTable entity) {
        return restaurantTableRepository.saveAndFlush(entity);
    }

    @Override
    public List<RestaurantTable> saveEntities(Iterable<RestaurantTable> entities) {
        List<RestaurantTable> savedEntities = restaurantTableRepository.save(entities);
        restaurantTableRepository.flush();
        return savedEntities;
    }
}
