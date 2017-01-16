package com.severett.restaurants.util;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RestaurantConstants {

    public static final String DATE_FORMAT = "MM/dd/yyyy";
    public static final String TIME_FORMAT = "MM/dd/yyyy HH:mm";
    
    public static final Map<Integer, DateHours> DATE_HOURS_MAP;
    static {
        DATE_HOURS_MAP = new HashMap<>();
        DATE_HOURS_MAP.put(Calendar.SUNDAY, new DateHours(10, 19));
        DATE_HOURS_MAP.put(Calendar.MONDAY, new DateHours(11, 21));
        DATE_HOURS_MAP.put(Calendar.TUESDAY, new DateHours(11, 21));
        DATE_HOURS_MAP.put(Calendar.WEDNESDAY, new DateHours(11, 21));
        DATE_HOURS_MAP.put(Calendar.THURSDAY, new DateHours(11, 21));
        DATE_HOURS_MAP.put(Calendar.FRIDAY, new DateHours(11, 22));
        DATE_HOURS_MAP.put(Calendar.SATURDAY, new DateHours(10, 22));
    }

}
