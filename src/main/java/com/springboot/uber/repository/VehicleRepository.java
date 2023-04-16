package com.springboot.uber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.uber.model.Vehicle;
import com.springboot.uber.model.VehicleBooking;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

//	Embedded SQL Query 1
	@Query(value = "SELECT * FROM vehicle", nativeQuery = true)
	List<Vehicle> findAll();

//	Embedded SQL Query 2
	@Query(value = "SELECT * FROM vehicle v where v.id=?1", nativeQuery = true)
	Long deleteAllById(Long id);

//	OLAP Query 1
	@Query(value = "SELECT v.id, v.company, v.model, COUNT(b.id) AS TotalBookings FROM vehicle v JOIN ride r ON v.id = r.vehicle_id JOIN booking b ON r.id = b.ride_id GROUP BY v.id, v.company, v.model ORDER BY TotalBookings DESC;", nativeQuery = true)
	List<VehicleBooking> findAllWithBookings();

	@Query(value = "SELECT DISTINCT r.vehicle_id FROM ride r WHERE r.status = 1", nativeQuery = true)
	List<Integer> findActiveVehicleIds();

}
