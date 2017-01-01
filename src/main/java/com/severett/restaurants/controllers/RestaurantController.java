package com.severett.restaurants.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RestaurantController {

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
