package com.springboot.uber.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DriverRevenue {
	@Id
	private Long id;
	private Long numBookings;
	private Long numRides;
	private Double revenue;

	public DriverRevenue() {

	}

	public DriverRevenue(Long id, Long numBookings, Long numRides, Double revenue) {
		super();
		this.id = id;
		this.numBookings = numBookings;
		this.numRides = numRides;
		this.revenue = revenue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumBookings() {
		return numBookings;
	}

	public void setNumBookings(Long numBookings) {
		this.numBookings = numBookings;
	}

	public Long getNumRides() {
		return numRides;
	}

	public void setNumRides(Long numRides) {
		this.numRides = numRides;
	}

	public Double getRevenue() {
		return revenue;
	}

	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}
}
