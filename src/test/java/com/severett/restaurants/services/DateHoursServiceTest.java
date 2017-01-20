package com.severett.restaurants.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.severett.restaurants.util.DateHours;

public class DateHoursServiceTest {

    private DateHoursService dateHoursService = new DateHoursServiceImpl();

    @Test
    public void properDatesTest() {
        String sundayDate = "01/15/2017";
        DateHours sundayDateHours = dateHoursService.getDateHours(sundayDate);
        assertNotNull(sundayDateHours);
        assertEquals(10, sundayDateHours.getStartHour());
        assertEquals(19, sundayDateHours.getEndHour());

        String fridayDate = "01/13/2017";
        DateHours fridayDateHours = dateHoursService.getDateHours(fridayDate);
        assertNotNull(fridayDateHours);
        assertEquals(11, fridayDateHours.getStartHour());
        assertEquals(22, fridayDateHours.getEndHour());
    }

    @Test
    public void improperDateTest() {
        String badSundayDate = "15/01/2017";
        DateHours badSundayDateHours = dateHoursService.getDateHours(badSundayDate);
        assertNull(badSundayDateHours);

        String textDate = "January 1st, 2017";
        DateHours textDateHours = dateHoursService.getDateHours(textDate);
        assertNull(textDateHours);
    }

    @Test
    public void noDateTest() {
        String nullDate = null;
        DateHours nullDateHours = dateHoursService.getDateHours(nullDate);
        assertNull(nullDateHours);

        String spacesDate = "       ";
        DateHours spacesDateHours = dateHoursService.getDateHours(spacesDate);
        assertNull(spacesDateHours);
    }
}
