package com.severett.restaurants.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DATE_HOURS")
public class DateHours implements Serializable {

	private static final long serialVersionUID = 282606846037861934L;

	@Id
	@GeneratedValue
	private Short id;
	
	@Column(name="START_HOUR", nullable=false)
	private Short startHour;
	
	@Column(name="END_HOUR", nullable=false)
	private Short endHour;
	
	protected DateHours () {
	}
	
	public DateHours(Short startHour, Short endHour) {
		this.startHour = startHour;
		this.endHour = endHour;
	}
	
	public Short getId() {
		return id;
	}
	
	public void setId(Short id) {
		this.id = id;
	}
	
	public Short getStartHour() {
		return startHour;
	}
	
	public void setStartHour(Short startHour) {
		this.startHour = startHour;
	}
	
	public Short getEndHour() {
		return endHour;
	}
	
	public void setEndHour(Short endHour) {
		this.endHour = endHour;
	}
}
