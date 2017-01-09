package com.severett.restaurants.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.severett.restaurants.model.RestaurantTable;

@Repository
public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Short> {

	@Query("select rt from RestaurantTable rt join rt.reservations r where rt.capacity = :partySize and not exists "
			+ "(select rv from Reservation rv where rv.restaurantTable.id = rt.id and (rv.startTime < :startTime and rv.endTime > :startTime) or "
			+ "(rv.startTime < :endTime or rv.endTime > :endTime))")
	public RestaurantTable findBySizeAndTimeWindow(@Param("partySize") Short partySize, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
	
}
