package com.severett.restaurants.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Table implements Serializable {

	private static final long serialVersionUID = 3355578631079182599L;

	@Id
	@GeneratedValue
	private Byte id;
	
	@Column(name="CAPACITY", nullable=false)
	private Byte capacity;
	
	@OneToMany(mappedBy="table")
	private Set<Reservation> reservations;
	
	protected Table() {
	}
	
	public Table(Byte capacity) {
		this.capacity = capacity;
	}
	
	public Byte getId() {
		return id;
	}
	
	public void setId(Byte id) {
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
