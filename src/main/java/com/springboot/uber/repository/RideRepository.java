package com.springboot.uber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.uber.model.Ride;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {

}