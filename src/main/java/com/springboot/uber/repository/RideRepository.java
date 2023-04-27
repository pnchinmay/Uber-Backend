package com.springboot.uber.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.uber.model.Ride;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {

	@Modifying
	@Transactional
	@Query(value = "UPDATE ride SET pickup = :pickup, destination = :destination, duration = :duration, fare = :fare, status = :status, distance = :distance, driver_id = :driverId, vehicle_id = :vehicleId WHERE id = :id", nativeQuery = true)
	void update(@Param("id") Long id, @Param("pickup") String pickup, @Param("destination") String destination,
			@Param("duration") int duration, @Param("fare") int fare, @Param("status") int status,
			@Param("distance") int distance, @Param("driverId") Long driver_id, @Param("vehicleId") Long vehicle_id);

}