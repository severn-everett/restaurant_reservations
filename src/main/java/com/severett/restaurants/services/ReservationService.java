package com.severett.restaurants.services;

import com.severett.restaurants.model.Guest;
import com.severett.restaurants.model.Reservation;
import com.severett.restaurants.model.RestaurantTable;

public interface ReservationService {

    public Reservation createReservation(RestaurantTable restaurantTable, Guest guest, String startTimeString);

}
