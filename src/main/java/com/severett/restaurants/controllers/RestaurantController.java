package com.severett.restaurants.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController {

	@RequestMapping("/")
	public String index() {
		return "Hello, world!";
	}
	
}
