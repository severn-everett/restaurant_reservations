package com.severett.restaurants.util;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

public class RestaurantConstants {

    public static final String DATE_FORMAT = "MM/dd/yyyy";
    public static final String TIME_FORMAT = "MM/dd/yyyy HH:mm";
    
    public static final Map<DayOfWeek, DateHours> DATE_HOURS_MAP;
    static {
        DATE_HOURS_MAP = new HashMap<>();
        DATE_HOURS_MAP.put(DayOfWeek.SUNDAY, new DateHours(10, 19));
        DATE_HOURS_MAP.put(DayOfWeek.MONDAY, new DateHours(11, 21));
        DATE_HOURS_MAP.put(DayOfWeek.TUESDAY, new DateHours(11, 21));
        DATE_HOURS_MAP.put(DayOfWeek.WEDNESDAY, new DateHours(11, 21));
        DATE_HOURS_MAP.put(DayOfWeek.THURSDAY, new DateHours(11, 21));
        DATE_HOURS_MAP.put(DayOfWeek.FRIDAY, new DateHours(11, 22));
        DATE_HOURS_MAP.put(DayOfWeek.SATURDAY, new DateHours(10, 22));
    }

}
