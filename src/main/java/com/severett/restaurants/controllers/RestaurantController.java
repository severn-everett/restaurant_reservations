package com.severett.restaurants.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.severett.restaurants.model.Reservation;
import com.severett.restaurants.model.RestaurantTable;
import com.severett.restaurants.services.ReservationService;
import com.severett.restaurants.services.RestaurantTableService;

@Controller
public class RestaurantController {
	
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	RestaurantTableService restaurantTableService;

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
	public String confirmReservation(Model model) {
		
		return "confirmReservation";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/confirm")
	public String saveReservation(Model model, @RequestParam(value="tableId") Short tableId,
			@RequestParam(value="startTime") String startTimeString) {
		RestaurantTable restaurantTable = restaurantTableService.findRestaurantTable(tableId);
		Reservation reservation = reservationService.createReservation(restaurantTable, startTimeString);
		if (reservation != null) {
			return "reserveSuccess";
		} else {
			return "reserveFailure";
		}
	}
}
