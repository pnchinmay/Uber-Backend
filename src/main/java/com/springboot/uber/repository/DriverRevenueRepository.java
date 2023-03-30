package com.springboot.uber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.uber.model.DriverRevenue;

@Repository
public interface DriverRevenueRepository extends JpaRepository<DriverRevenue, Long> {

}
