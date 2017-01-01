package com.severett.restaurants.model;

import java.time.LocalDate;

public class Reservation {

	private Integer id;
	
	private Table table;
	
	private LocalDate startTime;
	
	private LocalDate endTime;
	
	protected Reservation() {
	}
	
	public Reservation(Table table, LocalDate startTime, LocalDate endTime) {
		this.table = table;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Table getTable() {
		return this.table;
	}
	
	public void setTable(Table table) {
		this.table = table;
	}
	
	public LocalDate getStartTime() {
		return this.startTime;
	}
	
	public void setStartTime(LocalDate startTime) {
		this.startTime = startTime;
	}
	
	public LocalDate getEndTime() {
		return this.endTime;
	}
	
	public void setEndTime(LocalDate endTime) {
		this.endTime = endTime;
	}
}
