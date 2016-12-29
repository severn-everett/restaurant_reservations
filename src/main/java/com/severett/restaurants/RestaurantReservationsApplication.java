package com.severett.restaurants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.severett.restaurants"})
public class RestaurantReservationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantReservationsApplication.class, args);
	}
}
