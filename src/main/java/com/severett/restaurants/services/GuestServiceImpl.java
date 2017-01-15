package com.severett.restaurants.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.severett.restaurants.model.Guest;
import com.severett.restaurants.repositories.GuestRepository;

@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    public GuestRepository guestRepository;

    @Override
    public Guest createGuest(String firstName, String lastName) {
        Guest guest = new Guest(firstName, lastName);
        return guestRepository.saveAndFlush(guest);
    }

}
