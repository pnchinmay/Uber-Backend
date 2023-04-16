package com.springboot.uber.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.uber.model.Vehicle;
import com.springboot.uber.repository.VehicleRepository;

@Service
public class VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;

	@Transactional
	public List<Vehicle> getUnassignedVehicles() {
		// Get all active ride vehicle IDs
		List<Integer> activeVehicleIds = vehicleRepository.findActiveVehicleIds();

		// Get all vehicle IDs
		List<Long> allVehicleIds = vehicleRepository.findAll().stream().map(Vehicle::getId)
				.collect(Collectors.toList());

		// Get unassigned vehicle IDs
		List<Long> unassignedVehicleIds = allVehicleIds.stream().filter(id -> {
			Long id2 = id;
			return !activeVehicleIds.contains(id2.intValue());
		}).collect(Collectors.toList());

		// Get unassigned vehicle objects
		List<Vehicle> unassignedVehicles = vehicleRepository.findAllById(unassignedVehicleIds);

		return unassignedVehicles;
	}
}