package com.severett.restaurants.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.severett.restaurants.model.RestaurantTable;

@Repository
public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Short> {

	@Query("select rt from RestaurantTable rt join rt.reservations r where rt.capacity = :partySize and rt.id not in "
			+ "(select rv.restaurantTable.id from Reservation rv where (rv.startTime < :startTime and rv.endTime > :startTime) or "
			+ "(rv.startTime < :endTime and rv.endTime > :endTime))")
	public List<RestaurantTable> findBySizeAndTimeWindow(@Param("partySize") Short partySize, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
	
}
