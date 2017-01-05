package com.severett.restaurants.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="DATE_HOURS")
public class RestaurantTable implements Serializable {

	private static final long serialVersionUID = 3355578631079182599L;

	@Id
	@GeneratedValue
	private Short id;
	
	@Column(name="CAPACITY", nullable=false)
	private Short capacity;
	
	@OneToMany(mappedBy="table")
	private Set<Reservation> reservations;
	
	protected RestaurantTable() {
	}
	
	public RestaurantTable(Short capacity) {
		this.capacity = capacity;
	}
	
	public Short getId() {
		return id;
	}
	
	public void setId(Short id) {
		this.id = id;
	}
	
	public Short getCapacity() {
		return capacity;
	}
	
	public void setCapacity(Short capacity) {
		this.capacity = capacity;
	}
	
	public Set<Reservation> getReservations() {
		return reservations;
	}
	
	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}
}
