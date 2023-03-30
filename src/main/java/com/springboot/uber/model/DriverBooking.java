package com.springboot.uber.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DriverBooking {
	@Id
	private Long id;
	private String firstName;
	private String lastName;
	private Long totalBookings;

	public DriverBooking(Long id, String firstName, String lastName, Long totalBookings) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.totalBookings = totalBookings;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getTotalBookings() {
		return totalBookings;
	}

	public void setTotalBookings(Long totalBookings) {
		this.totalBookings = totalBookings;
	}

}
