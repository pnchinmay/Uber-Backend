package com.springboot.uber.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vehicle")
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String color;
	private String typeFuel;
	private String typeRide;
	private int wifi;
	private int ac;
	private String company;
	private String model;

	@Column(unique = true)
	private String VIN;

	public Vehicle() {
	}

	public Vehicle(String color, String typeFuel, String typeRide, int wifi, int ac, String company, String model,
			String VIN) {
		super();
		this.color = color;
		this.typeFuel = typeFuel;
		this.typeRide = typeRide;
		this.wifi = wifi;
		this.ac = ac;
		this.company = company;
		this.model = model;
		this.VIN = VIN;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTypeFuel() {
		return typeFuel;
	}

	public void setTypeFuel(String typeFuel) {
		this.typeFuel = typeFuel;
	}

	public String getTypeRide() {
		return typeRide;
	}

	public void setTypeRide(String typeRide) {
		this.typeRide = typeRide;
	}

	public int getWifi() {
		return wifi;
	}

	public void setWifi(int wifi) {
		this.wifi = wifi;
	}

	public int getAc() {
		return ac;
	}

	public void setAc(int ac) {
		this.ac = ac;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVIN() {
		return VIN;
	}

	public void setVIN(String vIN) {
		VIN = vIN;
	}

}
