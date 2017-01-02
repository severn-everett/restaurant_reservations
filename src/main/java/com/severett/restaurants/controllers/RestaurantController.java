package com.severett.restaurants.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.severett.restaurants.services.ReservationService;

@Controller
public class RestaurantController {
	
	@Autowired
	ReservationService reservationService;

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
	public String saveReservation(Model model) {
		
		return "reserveSuccess";
	}
}
