package com.severett.restaurants.services;

import com.severett.restaurants.model.Guest;

public interface GuestService extends EntityService<Guest> {

    public Guest createGuest(String firstName, String lastName);

}
