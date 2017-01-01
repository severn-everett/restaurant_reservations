package com.severett.restaurants.model;

import java.util.Set;

public class Table {

	private Integer id;
	
	private Byte capacity;
	
	private Set<Reservation> reservations;
	
	protected Table() {
	}
	
	public Table(Byte capacity) {
		this.capacity = capacity;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Byte getCapacity() {
		return capacity;
	}
	
	public void setCapacity(Byte capacity) {
		this.capacity = capacity;
	}
	
	public Set<Reservation> getReservations() {
		return reservations;
	}
	
	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}
}
