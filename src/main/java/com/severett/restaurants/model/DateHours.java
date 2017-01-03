package com.severett.restaurants.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class DateHours implements Serializable {

	private static final long serialVersionUID = 282606846037861934L;

	@Id
	@GeneratedValue
	private Short id;
	
	@Column(name="START_HOUR", nullable=false)
	private Integer startHour;
	
	@Column(name="END_HOUR", nullable=false)
	private Integer endHour;
	
	protected DateHours () {
	}
	
	public DateHours(Integer startHour, Integer endHour) {
		this.startHour = startHour;
		this.endHour = endHour;
	}
	
	public Short getId() {
		return id;
	}
	
	public void setId(Short id) {
		this.id = id;
	}
	
	public Integer getStartHour() {
		return startHour;
	}
	
	public void setStartHour(Integer startHour) {
		this.startHour = startHour;
	}
	
	public Integer getEndHour() {
		return endHour;
	}
	
	public void setEndHour(Integer endHour) {
		this.endHour = endHour;
	}
}
