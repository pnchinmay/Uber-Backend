package com.springboot.uber.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.uber.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
	@Modifying
	@Transactional
	@Query(value = "UPDATE booking SET status = :status, booking_time = :bookingTime, start_time = :startTime, end_time = :endTime, ride_id = :rideId WHERE id = :id", nativeQuery = true)
	void update(@Param("id") Long id, @Param("status") int status, @Param("bookingTime") String bookingTime,
			@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("rideId") Long rideId);

}
