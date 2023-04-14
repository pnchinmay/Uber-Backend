package com.springboot.uber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.uber.model.Driver;
import com.springboot.uber.model.DriverBooking;
import com.springboot.uber.model.DriverRevenue;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

//	OLAP Query 2
	@Query(value = "SELECT AVG(CAST(driver.age AS UNSIGNED)) AS avg_age\n" + "FROM booking\n"
			+ "JOIN ride ON booking.ride_id = ride.id\n" + "JOIN driver ON ride.driver_id = driver.id\n"
			+ "", nativeQuery = true)
	Double findDriversAvgAge();

//	OLAP Query 3
	@Query(value = "SELECT driver.id, driver.first_name, driver.last_name, COUNT(booking.id) AS total_bookings FROM driver LEFT JOIN ride ON driver.id = ride.driver_id LEFT JOIN booking ON ride.id = booking.ride_id GROUP BY driver.id;", nativeQuery = true)
	List<DriverBooking> findDriversBookings();

//	OLAP Query 4
	@Query(value = "SELECT d.id AS driver_id, COUNT(DISTINCT b.id) AS bookings, COUNT(DISTINCT r.id) AS rides, SUM(r.fare) AS revenue FROM driver d LEFT JOIN vehicle v ON d.vehicle_id = v.id LEFT JOIN ride r ON d.id = r.driver_id AND v.id = r.vehicle_id LEFT JOIN booking b ON r.id = b.ride_id GROUP BY d.id;", nativeQuery = true)
	List<DriverRevenue> findDriversRevenues();

}