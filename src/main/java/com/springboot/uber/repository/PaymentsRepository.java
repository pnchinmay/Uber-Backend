package com.springboot.uber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.uber.model.Payments;

@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Long> {

}
