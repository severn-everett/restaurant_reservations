package com.severett.restaurants.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.severett.restaurants.model.Guest;
import com.severett.restaurants.model.Reservation;
import com.severett.restaurants.model.RestaurantTable;
import com.severett.restaurants.services.GuestService;
import com.severett.restaurants.services.ReservationService;
import com.severett.restaurants.services.RestaurantTableService;

@Controller
public class RestaurantController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    RestaurantTableService restaurantTableService;

    @Autowired
    GuestService guestService;

    @RequestMapping(method=RequestMethod.GET, value="/")
    public String index(Model model) {
        model.addAttribute("restaurantName", "Test Restaurant");
        return "index";
    }

    @RequestMapping(method=RequestMethod.GET, value="/add")
    public String addReservation(Model model) {
        return "addReservation";
    }

    @RequestMapping(method=RequestMethod.GET, value="/confirm")
    public String confirmReservation(Model model, @RequestParam(value="partySize") Short partySize, @RequestParam(value="targetTime") String targetTimeString) {
        RestaurantTable openRestaurantTable = restaurantTableService.getOpenRestaurantTable(partySize, targetTimeString);
        if (openRestaurantTable != null) {
            model.addAttribute("tableId", openRestaurantTable.getId());
            model.addAttribute("partySize", partySize);
            model.addAttribute("targetTime", targetTimeString);
            return "confirmReservation";
        } else {
            return "noTableAvailable";
        }
    }

    @RequestMapping(method=RequestMethod.POST, value="/confirm")
    public String saveReservation(Model model, @RequestParam(value="tableId") Short tableId,
            @RequestParam(value="targetTime") String startTimeString, @RequestParam(value="firstName") String firstName, @RequestParam(value="lastName") String lastName) {
        RestaurantTable restaurantTable = restaurantTableService.findRestaurantTable(tableId);
        Guest guest = guestService.createGuest(firstName, lastName);
        Reservation reservation = reservationService.createReservation(restaurantTable, guest, startTimeString);
        if (reservation != null) {
            return "reserveSuccess";
        } else {
            return "reserveFailure";
        }
    }
}
