package com.springboot.uber.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.uber.exception.ResourceNotFoundException;
import com.springboot.uber.model.Driver;
import com.springboot.uber.model.DriverBooking;
import com.springboot.uber.model.DriverRevenue;
import com.springboot.uber.repository.DriverRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*")
public class DriverController {

	@Autowired
	private DriverRepository driverRepository;

	@GetMapping("/drivers")
	public List<Driver> getAllDrivers() {
		return driverRepository.findAll();
	}

//	OLAP Query 2
	@GetMapping("/drivers/averageAge")
	public Double getDriversAvgAge() {
		return driverRepository.findDriversAvgAge();
	}

//	OLAP Query 3
	@GetMapping("/drivers/bookings")
	public List<DriverBooking> getDriversBookings() {
		return driverRepository.findDriversBookings();
	}

//	OLAP Query 4
	@GetMapping("/drivers/revenues")
	public List<DriverRevenue> getDriversRevenues() {
		return driverRepository.findDriversRevenues();
	}

	@PostMapping("/drivers")
	public Driver createDriver(@RequestBody Driver driver) {
		return driverRepository.save(driver);
	}

	@GetMapping("/drivers/{id}")
	public ResponseEntity<Driver> getDriverbyId(@PathVariable Long id) {
		Driver driver = driverRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No driver of id: " + id));
		return ResponseEntity.ok(driver);
	}

	@PutMapping("/drivers/{id}")
	public ResponseEntity<Driver> updateDriverbyId(@PathVariable Long id, @RequestBody Driver driverDetails) {
		Driver driver = driverRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No driver of id: " + id));
		driver.setFirstName(driverDetails.getFirstName());
		driver.setLastName(driverDetails.getLastName());
		driver.setAddrStreet(driverDetails.getAddrStreet());
		driver.setAddrLocality(driverDetails.getAddrLocality());
		driver.setAddrCity(driverDetails.getAddrCity());
		driver.setAddrState(driverDetails.getAddrState());
		driver.setAddrPin(driverDetails.getAddrPin());
		driver.setPhone(driverDetails.getPhone());
		driver.setGender(driverDetails.getGender());
		driver.setAge(driverDetails.getAge());
		driver.setAadhar(driverDetails.getAadhar());
		driver.setExperience(driverDetails.getExperience());
		driver.setPayId(driverDetails.getPayId());
		driver.setEmail(driverDetails.getEmail());
		driver.setRating(driverDetails.getRating());
		driver.setDrivingLicense(driverDetails.getDrivingLicense());
		driver.setVehicle(driverDetails.getVehicle());

		Driver updatedDriver = driverRepository.save(driver);
		return ResponseEntity.ok(updatedDriver);
	}

	@DeleteMapping("/drivers/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteDriver(@PathVariable Long id) {
		Driver driver = driverRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No driver of id: " + id));
		driverRepository.delete(driver);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}