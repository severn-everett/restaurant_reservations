package com.severett.restaurants.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="RESERVATION")
public class Reservation implements Serializable {

	private static final long serialVersionUID = -9096118012365741262L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RESTAURANT_TABLE_ID")
	private RestaurantTable restaurantTable;
	
	@Column(name="START_TIME", nullable=false)
	private Date startTime;
	
	@Column(name="END_TIME", nullable=false)
	private Date endTime;
	
	protected Reservation() {
	}
	
	public Reservation(RestaurantTable restaurantTable, Date startTime, Date endTime) {
		this.restaurantTable = restaurantTable;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public RestaurantTable getRestaurantTable() {
		return this.restaurantTable;
	}
	
	public void setRestaurantTable(RestaurantTable restaurantTable) {
		this.restaurantTable = restaurantTable;
	}
	
	public Date getStartTime() {
		return this.startTime;
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getEndTime() {
		return this.endTime;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
