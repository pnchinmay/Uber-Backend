package com.springboot.uber.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.uber.model.Payments;

@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Long> {
	@Modifying
	@Transactional
	@Query("UPDATE Payments p SET p.status = :status WHERE p.id = :id")
	void updateStatus(@Param("id") Long id, @Param("status") int status);

	@Modifying
	@Transactional
	@Query("UPDATE Payments p SET p.method = :method WHERE p.id = :id")
	void updateMethod(@Param("id") Long id, @Param("method") String method);

	@Query(value = "SELECT p.id as id FROM payments p where p.booking_id = ?1", nativeQuery = true)
	Long findByBookingId(Long bookingId);
}
