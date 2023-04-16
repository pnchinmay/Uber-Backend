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
import com.springboot.uber.model.Vehicle;
import com.springboot.uber.model.VehicleBooking;
import com.springboot.uber.repository.VehicleRepository;
import com.springboot.uber.service.VehicleService;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*")
public class VehicleController {

	@Autowired
	private VehicleRepository vehicleRepository;
	@Autowired
	private VehicleService vehicleService;

//	OLAP Query 1
	@GetMapping("/vehicles/bookings")
	public List<VehicleBooking> getAllVehiclesWithBookings() {
		return vehicleRepository.findAllWithBookings();
	}

	@GetMapping("/vehicles")
	public List<Vehicle> getAllVehicles() {
		return vehicleRepository.findAll();
	}

	@GetMapping("/vehicles/unassigned")
	public ResponseEntity<List<Vehicle>> getUnassignedVehicles() {
		List<Vehicle> unassignedVehicles = vehicleService.getUnassignedVehicles();
		return ResponseEntity.ok(unassignedVehicles);
	}

	@PostMapping("/vehicles")
	public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}

	@GetMapping("/vehicles/{id}")
	public ResponseEntity<Vehicle> getVehiclebyId(@PathVariable Long id) {
		Vehicle vehicle = vehicleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No vehicle of id: " + id));
		return ResponseEntity.ok(vehicle);
	}

	@PutMapping("/vehicles/{id}")
	public ResponseEntity<Vehicle> updateVehiclebyId(@PathVariable Long id, @RequestBody Vehicle vehicleDetails) {
		Vehicle vehicle = vehicleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No vehicle of id: " + id));
		vehicle.setColor(vehicleDetails.getColor());
		vehicle.setTypeFuel(vehicleDetails.getTypeFuel());
		vehicle.setTypeRide(vehicleDetails.getTypeRide());
		vehicle.setWifi(vehicleDetails.getWifi());
		vehicle.setAc(vehicleDetails.getAc());
		vehicle.setCompany(vehicleDetails.getCompany());
		vehicle.setModel(vehicleDetails.getModel());
		vehicle.setVIN(vehicleDetails.getVIN());

		Vehicle updatedVehicle = vehicleRepository.save(vehicle);
		return ResponseEntity.ok(updatedVehicle);
	}

	// delete vehicle
	@DeleteMapping("/vehicles/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteVehicle(@PathVariable Long id) {
		Vehicle vehicle = vehicleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No vehicle of id: " + id));
		vehicleRepository.delete(vehicle);
		Map<String, Boolean> response = new HashMap<>();

		vehicleRepository.deleteAllById(id);
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
