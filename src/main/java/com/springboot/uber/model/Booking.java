package com.springboot.uber.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "booking", uniqueConstraints = @UniqueConstraint(columnNames = { "ride_id" }))
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private int status;
	private String bookingTime;
	private String startTime;
	private String endTime;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "ride_id", referencedColumnName = "id")
	private Ride ride;

	public Booking() {
	}

	public Booking(int status, String bookingTime, String startTime, String endTime) {
		super();
		this.status = status;
		this.bookingTime = bookingTime;
		this.startTime = startTime;
		this.endTime = endTime;
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

	public String getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Ride getRide() {
		return ride;
	}

	public void setRide(Ride ride) {
		this.ride = ride;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", status=" + status + ", bookingTime=" + bookingTime + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", ride=" + ride + "]";
	}

}