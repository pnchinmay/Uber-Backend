package com.springboot.uber.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

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
	private String email;
	private String password;
	private int age;
	private String emerContact;
	private int subscription;
	private String gender;

	@Column(unique = true)
	private String aadhar;

	@Column(unique = true)
	private String payId;

	public User() {
	}

	public User(String firstName, String lastName, String addrStreet, String addrLocality, String addrCity,
			String addrState, String addrPin, String phone, String email, String password, int age, String emerContact,
			int subscription, String gender, String aadhar, String payId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.addrStreet = addrStreet;
		this.addrLocality = addrLocality;
		this.addrCity = addrCity;
		this.addrState = addrState;
		this.addrPin = addrPin;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.age = age;
		this.emerContact = emerContact;
		this.subscription = subscription;
		this.gender = gender;
		this.aadhar = aadhar;
		this.payId = payId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmerContact() {
		return emerContact;
	}

	public void setEmerContact(String emerContact) {
		this.emerContact = emerContact;
	}

	public int getSubscription() {
		return subscription;
	}

	public void setSubscription(int subscription) {
		this.subscription = subscription;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

}
