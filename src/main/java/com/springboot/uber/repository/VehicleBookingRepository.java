package com.springboot.uber.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.uber.model.VehicleBooking;

public interface VehicleBookingRepository extends JpaRepository<VehicleBooking, Long> {

}
