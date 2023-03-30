package com.springboot.uber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.uber.model.DriverBooking;

@Repository
public interface DriverBookingRepository extends JpaRepository<DriverBooking, Long> {

}
