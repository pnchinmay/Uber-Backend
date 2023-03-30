package com.springboot.uber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.uber.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
