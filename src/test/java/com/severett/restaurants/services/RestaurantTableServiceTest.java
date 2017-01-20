package com.severett.restaurants.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.severett.restaurants.model.RestaurantTable;
import com.severett.restaurants.repositories.RestaurantTableRepository;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantTableServiceTest {

    @Mock
    private RestaurantTableRepository restaurantTableRepository;

    @InjectMocks
    private RestaurantTableService restaurantTableService = new RestaurantTableServiceImpl();
    
    private final short groupSize = (short) 4;
    private final RestaurantTable testTable = new RestaurantTable(groupSize);

    @Before
    public void setup() {
        when(restaurantTableRepository.saveAndFlush(anyObject())).then(AdditionalAnswers.returnsFirstArg());
        when(restaurantTableRepository.findBySizeAndTimeWindow(anyObject(), anyObject(), anyObject())).thenReturn(Arrays.asList(testTable));
    }

    @Test
    public void findTableTest() {
        RestaurantTable foundTable = restaurantTableService.getOpenRestaurantTable(groupSize, "01/15/2017 12:00");
        assertNotNull(foundTable);
    }

    @Test
    public void busyTimeTest() {
        when(restaurantTableRepository.findBySizeAndTimeWindow(anyObject(), anyObject(), anyObject())).thenReturn(null);
        RestaurantTable busyTimeTable = restaurantTableService.getOpenRestaurantTable(groupSize, "01/15/2017 17:00");
        assertNull(busyTimeTable);
    }

    @Test
    public void badTimeTest() {
        RestaurantTable badTimeTable = restaurantTableService.getOpenRestaurantTable(groupSize, "15/01/2017 17:00");
        assertNull(badTimeTable);
    }

    @Test
    public void noTimeTest() {
        RestaurantTable nullTimeTable = restaurantTableService.getOpenRestaurantTable(groupSize, null);
        assertNull(nullTimeTable);

        RestaurantTable blankTimeTable = restaurantTableService.getOpenRestaurantTable(groupSize, "      ");
        assertNull(blankTimeTable);
    }
}
