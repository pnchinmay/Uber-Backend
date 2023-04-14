package com.springboot.uber.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "driver", uniqueConstraints = @UniqueConstraint(columnNames = { "vehicle_id" }))
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String firstName;
	private String lastName;
	private String addrStreet;
	private String addrLocality;
	private String addrCity;
	private String addrState;
	private String addrPin;
	private String phone;
	private char gender;
	private int age;

	@Column(unique = true)
	private String aadhar;
	private float experience;

	@Column(unique = true)
	private String payId;
	private String email;
	private int rating;

	@Column(unique = true)
	private String drivingLicense;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "vehicle_id", referencedColumnName = "id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Vehicle vehicle;

	@PrePersist
	@PreUpdate
	public void validate() {
		if (vehicle == null) {
			throw new IllegalStateException("Driver must have a vehicle");
		}
	}

	public Driver() {
	}

	public Driver(String firstName, String lastName, String addrStreet, String addrLocality, String addrCity,
			String addrState, String addrPin, String phone, char gender, int age, String aadhar, float experience,
			String payId, String email, int rating, String drivingLicense) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.addrStreet = addrStreet;
		this.addrLocality = addrLocality;
		this.addrCity = addrCity;
		this.addrState = addrState;
		this.addrPin = addrPin;
		this.phone = phone;
		this.gender = gender;
		this.age = age;
		this.aadhar = aadhar;
		this.experience = experience;
		this.payId = payId;
		this.email = email;
		this.rating = rating;
		this.drivingLicense = drivingLicense;
	}

	public boolean hasVehicle() {
		return vehicle != null;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getAddrStreet() {
		return addrStreet;
	}

	public void setAddrStreet(String addrStreet) {
		this.addrStreet = addrStreet;
	}

	public String getAddrLocality() {
		return addrLocality;
	}

	public void setAddrLocality(String addrLocality) {
		this.addrLocality = addrLocality;
	}

	public String getAddrCity() {
		return addrCity;
	}

	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}

	public String getAddrState() {
		return addrState;
	}

	public void setAddrState(String addrState) {
		this.addrState = addrState;
	}

	public String getAddrPin() {
		return addrPin;
	}

	public void setAddrPin(String addrPin) {
		this.addrPin = addrPin;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public float getExperience() {
		return experience;
	}

	public void setExperience(float experience) {
		this.experience = experience;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getDrivingLicense() {
		return drivingLicense;
	}

	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}
