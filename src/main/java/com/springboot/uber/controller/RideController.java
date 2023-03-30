package com.springboot.uber.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.uber.exception.ResourceNotFoundException;
import com.springboot.uber.model.Ride;
import com.springboot.uber.repository.RideRepository;

@RestController
@RequestMapping("/api/v1/")
public class RideController {

	@Autowired
	private RideRepository rideRepository;

	@GetMapping("/rides")
	public List<Ride> getAllRides() {
		return rideRepository.findAll();
	}

	@PostMapping("/rides")
	public Ride createRide(@RequestBody Ride ride) {
		return rideRepository.save(ride);
	}

	@GetMapping("/ride/{id}")
	public ResponseEntity<Ride> getRidebyId(@PathVariable Long id) {
		Ride ride = rideRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No ride of id: " + id));
		return ResponseEntity.ok(ride);
	}

	@PutMapping("/rides/{id}")
	public ResponseEntity<Ride> updateRidebyId(@PathVariable Long id, @RequestBody Ride rideDetails) {
		Ride ride = rideRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No ride of id: " + id));
		ride.setPickup(rideDetails.getPickup());
		ride.setDestination(rideDetails.getDestination());
		ride.setDuration(rideDetails.getDuration());
		ride.setFare(rideDetails.getFare());
		ride.setStatus(rideDetails.getStatus());
		ride.setDistance(rideDetails.getDistance());
		ride.setVehicle(rideDetails.getVehicle());
		ride.setDriver(rideDetails.getDriver());

		Ride updatedRide = rideRepository.save(ride);
		return ResponseEntity.ok(updatedRide);
	}

	@DeleteMapping("/rides/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteRide(@PathVariable Long id) {
		Ride ride = rideRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No ride of id: " + id));
		rideRepository.delete(ride);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}