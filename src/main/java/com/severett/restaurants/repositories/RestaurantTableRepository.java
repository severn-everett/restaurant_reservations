package com.severett.restaurants.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.severett.restaurants.model.RestaurantTable;

@Repository
public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Short> {

	@Query("select rt from RestaurantTable rt where rt.capacity = :partySize limit 1")
	public RestaurantTable findBySizeAndTimeWindow(@Param("partySize") Short partySize, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
	
}
