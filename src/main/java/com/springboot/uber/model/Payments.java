package com.springboot.uber.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "payments", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "user_id", "booking_id", "driver_id" }) })
public class Payments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private int status;
	private String method;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "booking_id", referencedColumnName = "id")
	private Booking booking;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "driver_id", referencedColumnName = "id")
	private Driver driver;

	public Payments() {
	}

	public Payments(int status, String method) {
		super();
		this.status = status;
		this.method = method;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
