package com.severett.restaurants.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.severett.restaurants.model.DateHours;

@Repository
public interface DateHoursRepository extends JpaRepository<DateHours, Short> {

}
