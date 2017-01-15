package com.severett.restaurants.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.severett.restaurants.model.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Integer> {

}
