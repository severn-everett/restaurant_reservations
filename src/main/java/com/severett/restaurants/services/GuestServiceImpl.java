package com.severett.restaurants.services;

import java.util.Date;
import java.util.List;

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
        Date currentTime = new Date();
        guest.setCreatedDate(currentTime);
        guest.setUpdatedDate(currentTime);
        return saveEntity(guest);
    }

    @Override
    public Guest saveEntity(Guest entity) {
        return guestRepository.saveAndFlush(entity);
    }

    @Override
    public List<Guest> saveEntities(Iterable<Guest> entities) {
        List<Guest> savedEntities = guestRepository.save(entities);
        guestRepository.flush();
        return savedEntities;
    }

}
