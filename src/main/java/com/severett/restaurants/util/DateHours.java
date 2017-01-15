package com.severett.restaurants.util;

import java.util.Calendar;
import java.util.Date;

public class DateHours {

    private int startHour;

    private int endHour;

    protected DateHours () {
    }

    public DateHours(int startHour, int endHour) {
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public int getStartHour() {
        return this.startHour;
    }

    public int getEndHour() {
        return this.endHour;
    }
    
    public boolean isWithinHours(Date selectedTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(selectedTime);
        calendar.set(Calendar.HOUR_OF_DAY, startHour);
        calendar.set(Calendar.MINUTE, 0);
        Date startTime = calendar.getTime();
        calendar.set(Calendar.HOUR_OF_DAY, endHour);
        Date endTime = calendar.getTime();
        return ((startTime.compareTo(selectedTime) <= 0) && (selectedTime.compareTo(endTime) <= 0));
    }
}
