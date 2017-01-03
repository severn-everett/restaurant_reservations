package com.severett.restaurants.services;

import org.springframework.stereotype.Service;

import com.severett.restaurants.model.RestaurantTable;
import com.severett.restaurantes.repositories.RestaurantTableRepository;

@Service
public class RestaurantTableServiceImpl implements RestaurantTableService {

	RestaurantTableRepository restaurantTableRepository;
	
	@Override
	public RestaurantTable findRestaurantTable(Short tableId) {
		return restaurantTableRepository.findOne(tableId);
	}

}
