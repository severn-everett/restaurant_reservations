package com.severett.restaurants.services;

import com.severett.restaurants.model.RestaurantTable;

public interface RestaurantTableService extends EntityService<RestaurantTable> {

    public RestaurantTable findRestaurantTable(Short tableId);

    public RestaurantTable getOpenRestaurantTable(Short partySize, String targetTimeString);

}
