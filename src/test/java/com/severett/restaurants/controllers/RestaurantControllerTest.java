package com.severett.restaurants.controllers;

import static org.mockito.Matchers.anyShort;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.severett.restaurants.model.Guest;
import com.severett.restaurants.model.Reservation;
import com.severett.restaurants.model.RestaurantTable;
import com.severett.restaurants.services.GuestService;
import com.severett.restaurants.services.ReservationService;
import com.severett.restaurants.services.RestaurantTableService;
import com.severett.restaurants.util.RestaurantConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestaurantControllerTest {
    
    final String BASE_URL = "http://localhost:8080/";

    @Mock
    ReservationService reservationService;

    @Mock
    RestaurantTableService restaurantTableService;

    @Mock
    GuestService guestService;

    @InjectMocks
    private RestaurantController restaurantController = new RestaurantController();
    
    private MockMvc mockMvc;

    private final Short partySize = 4;
    private final Short tableId = 1;
    private final Short badTableId = 20;
    private final String availableTime = "01/15/2017 12:00";
    private final String endTime = "01/15/2017 13:00";
    private final String unavailableTime = "01/15/2017 11:00";
    private final String firstName = "John";
    private final String lastName = "Doe";
    private final Guest guest = new Guest(firstName, lastName);
    private RestaurantTable restaurantTable = new RestaurantTable(partySize);
    private Reservation reservation;

    @Before
    public void setup() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(RestaurantConstants.TIME_FORMAT);
        LocalDateTime startDT = LocalDateTime.parse(availableTime, formatter);
        Date startDate = Date.from(startDT.atZone(ZoneId.systemDefault()).toInstant());
        LocalDateTime endDT = LocalDateTime.parse(endTime, formatter);
        Date endDate = Date.from(endDT.atZone(ZoneId.systemDefault()).toInstant());
        reservation = new Reservation(restaurantTable, guest, startDate, endDate);
        
        restaurantTable.setId(tableId);
        when(restaurantTableService.getOpenRestaurantTable(partySize, availableTime))
            .thenReturn(restaurantTable);
        when(restaurantTableService.getOpenRestaurantTable(partySize, unavailableTime))
            .thenReturn(null);
        when(restaurantTableService.findRestaurantTable(anyShort())).thenReturn(null);
        when(restaurantTableService.findRestaurantTable(tableId)).thenReturn(restaurantTable);
        when(guestService.createGuest(firstName, lastName)).thenReturn(guest);
        when(reservationService.createReservation(restaurantTable, guest, availableTime))
            .thenReturn(reservation);
        when(reservationService.createReservation(restaurantTable, guest, unavailableTime))
            .thenReturn(null);
        this.mockMvc = MockMvcBuilders.standaloneSetup(restaurantController).build();
    }

    @Test
    public void confirmReservationTest() throws Exception {
        mockMvc.perform(get("/confirm")
                .param("partySize", partySize.toString())
                .param("targetTime", availableTime)
                .accept(MediaType.parseMediaType("text/html")))
                .andExpect(status().isOk())
                .andExpect(view().name("confirmReservation"))
                .andExpect(model().attribute("tableId", tableId))
                .andExpect(model().attribute("partySize", partySize))
                .andExpect(model().attribute("targetTime", availableTime));
    }

    @Test
    public void noOpenTableTest() throws Exception {
        mockMvc.perform(get("/confirm")
                .param("partySize", partySize.toString())
                .param("targetTime", unavailableTime)
                .accept(MediaType.parseMediaType("text/html")))
                .andExpect(status().isOk())
                .andExpect(view().name("noTableAvailable"));
    }

    @Test
    public void createReservationTest() throws Exception {
        mockMvc.perform(post("/confirm")
                .param("tableId", tableId.toString())
                .param("targetTime", availableTime)
                .param("firstName", firstName)
                .param("lastName", lastName)
                .accept(MediaType.parseMediaType("text/html")))
                .andExpect(status().isOk())
                .andExpect(view().name("reserveSuccess"));
    }

    @Test
    public void badTableIdReservationTest() throws Exception {
        mockMvc.perform(post("/confirm")
                .param("tableId", badTableId.toString())
                .param("targetTime", availableTime)
                .param("firstName", firstName)
                .param("lastName", lastName)
                .accept(MediaType.parseMediaType("text/html")))
                .andExpect(status().isOk())
                .andExpect(view().name("reserveFailure"));
    }
    

    @Test
    public void unavailableTimeReservationTest() throws Exception {
        mockMvc.perform(post("/confirm")
                .param("tableId", tableId.toString())
                .param("targetTime", unavailableTime)
                .param("firstName", firstName)
                .param("lastName", lastName)
                .accept(MediaType.parseMediaType("text/html")))
                .andExpect(status().isOk())
                .andExpect(view().name("reserveFailure"));
    }
}
