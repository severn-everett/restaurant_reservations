package com.severett.restaurants.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.severett.restaurants.model.Guest;
import com.severett.restaurants.model.Reservation;
import com.severett.restaurants.model.RestaurantTable;
import com.severett.restaurants.repositories.ReservationRepository;

@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService = new ReservationServiceImpl();
    
    private final Guest testGuest = new Guest("John", "Doe");
    private final RestaurantTable testTable = new RestaurantTable((short) 4);

    @Before
    public void setup() {
        when(reservationRepository.saveAndFlush(anyObject())).then(AdditionalAnswers.returnsFirstArg());
    }

    @Test
    public void properReservationTest() {
        Reservation reservation = reservationService.createReservation(testTable, testGuest, "01/15/2017 12:00");
        assertNotNull(reservation);
        assertEquals("Sun Jan 15 12:00:00 EST 2017", reservation.getStartTime().toString());
        assertEquals("Sun Jan 15 13:00:00 EST 2017", reservation.getEndTime().toString());
    }

    @Test
    public void improperTimeFormatTest() {
        Reservation badFormatReservation = reservationService.createReservation(testTable, testGuest, "15/01/2017 12:00");
        assertNull(badFormatReservation);

        Reservation onlyDateReservation = reservationService.createReservation(testTable, testGuest, "01/15/2017");
        assertNull(onlyDateReservation);

        Reservation textTimeReservation = reservationService.createReservation(testTable, testGuest, "January 15, 2017 12:00");
        assertNull(textTimeReservation);
    }

    @Test
    public void noTimeTest() {
        Reservation nullTimeReservation = reservationService.createReservation(testTable, testGuest, null);
        assertNull(nullTimeReservation);

        Reservation blankTimeReservation = reservationService.createReservation(testTable, testGuest, "      ");
        assertNull(blankTimeReservation);
    }
}
