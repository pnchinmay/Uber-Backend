package com.springboot.uber.model;

import java.math.BigInteger;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VehicleBooking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;
	private String company;
	private String model;
	private long totalBookings;

	public VehicleBooking() {
	}

	public VehicleBooking(Map<String, Object> map) {
		this.id = (BigInteger) map.get("id");
		this.company = (String) map.get("company");
		this.model = (String) map.get("model");
		this.totalBookings = ((Number) map.get("TotalBookings")).longValue();
	}

//	public VehicleBooking(BigInteger id, String company, String model, Long totalBookings) {
//		super();
//		this.id = id;
//		this.company = company;
//		this.model = model;
//		this.totalBookings = totalBookings;
//	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
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

	public Long getTotalBookings() {
		return totalBookings;
	}

	public void setTotalBookings(Long totalBookings) {
		this.totalBookings = totalBookings;
	}

}
