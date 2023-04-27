package com.springboot.uber.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.lang.NonNull;

@Entity
//@Table(name = "ride", uniqueConstraints = { @UniqueConstraint(columnNames = { "vehicle_id", "driver_id" }) })
public class Ride {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NonNull
	private String pickup;
	@NonNull
	private String destination;
	@NonNull
	private int duration;
	private int fare;
	@NonNull
	private int status;
	@NonNull
	private int distance;

	@NonNull
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "vehicle_id", referencedColumnName = "id")
	private Vehicle vehicle;

	@NonNull
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "driver_id", referencedColumnName = "id")
	private Driver driver;

	@PrePersist
	@PreUpdate
	public void validate() {
		if (driver.getVehicle() == null) {
			throw new IllegalStateException("Driver must have a vehicle");
		}

//		System.out.println(vehicle.getId());
//		System.out.println(driver.getVehicle().getId());
		if (vehicle.getId() != driver.getVehicle().getId()) {
			throw new IllegalArgumentException("Vehicle does not match the driver's vehicle.");
		}
	}

	public Ride() {
	}

	public Ride(String pickup, String destination, int duration, int fare, int status, int distance) {
		super();
		this.pickup = pickup;
		this.destination = destination;
		this.duration = duration;
		this.fare = fare;
		this.status = status;
		this.distance = distance;
	}

	public Ride(String pickup, String destination, int duration, int fare, int status, int distance, Vehicle vehicle,
			Driver driver) {
		super();
		this.pickup = pickup;
		this.destination = destination;
		this.duration = duration;
		this.fare = fare;
		this.status = status;
		this.distance = distance;
		this.vehicle = vehicle;
		this.driver = driver;
		if (vehicle != null && !vehicle.equals(driver.getVehicle())) {
			throw new IllegalArgumentException("Vehicle in ride must match driver's vehicle.");
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPickup() {
		return pickup;
	}

	public void setPickup(String pickup) {
		this.pickup = pickup;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

}
