package com.springboot.uber.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.uber.exception.ResourceNotFoundException;
import com.springboot.uber.model.Payments;
import com.springboot.uber.repository.BookingRepository;
import com.springboot.uber.repository.PaymentsRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*")
public class PaymentsController {

	@Autowired
	private PaymentsRepository paymentsRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@GetMapping("/payments")
	public List<Payments> getAllPayments() {
		return paymentsRepository.findAll();
	}

	@PostMapping("/payments")
	public Payments createPayments(@RequestBody Payments payments) {
		return paymentsRepository.save(payments);
	}

	@GetMapping("/payments/{id}")
	public ResponseEntity<Payments> getPaymentsbyId(@PathVariable Long id) {
		Payments payments = paymentsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No payment of id: " + id));
		return ResponseEntity.ok(payments);
	}

	@GetMapping("/payments/bookingId/{bookingId}")
	public ResponseEntity<Payments> getPaymentsbyBookingId(@PathVariable Long bookingId) {
		Long paymentId = paymentsRepository.findByBookingId(bookingId);
		if (paymentId == null) {
			throw new ResourceNotFoundException("No payment for booking id: " + bookingId);
		}
		Payments payments = paymentsRepository.findById(paymentId)
				.orElseThrow(() -> new ResourceNotFoundException("No payment of id: " + paymentId));
		return ResponseEntity.ok(payments);
	}

//	@PutMapping("/payments/{id}")
//	public ResponseEntity<Payments> updatePaymentsbyId(@PathVariable Long id, @RequestBody Payments paymentDetails) {
//		Payments payments = paymentsRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("No payment of id: " + id));
//		payments.setStatus(paymentDetails.getStatus());
//		payments.setMethod(paymentDetails.getMethod());
//		payments.setUser(paymentDetails.getUser());
////		entityManager.detach(payments.getBooking());
//		Optional<Booking> booking1 = bookingRepository.findById(paymentDetails.getBooking().getId());
//		Booking booking = booking1.orElse(null);
////		payments.setBooking(paymentDetails.getBooking());
//		payments.setBooking(booking);
////		entityManager.detach(payments.getDriver());
////		Driver driver = entityManager.find(Driver.class, paymentDetails.getDriver().getId());
//
//		payments.setDriver(paymentDetails.getDriver());
//
////		Payments updatedPayments = entityManager.merge(payments);
//		Payments updatedPayments = paymentsRepository.save(payments);
//		return ResponseEntity.ok(updatedPayments);
//	}

	@PutMapping("/payments/{id}")
	public ResponseEntity<Payments> updatePaymentStatus(@PathVariable Long id, @RequestBody Payments paymentDetails) {
		int status = paymentDetails.getStatus();
		String method = paymentDetails.getMethod();
		paymentsRepository.updateMethod(id, method);
		paymentsRepository.updateStatus(id, status);
		Payments payments = paymentsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No payment of id: " + id));
		return ResponseEntity.ok(payments);
	}

	@DeleteMapping("/payments/{id}")
	public ResponseEntity<Map<String, Boolean>> deletePayments(@PathVariable Long id) {
		Payments payments = paymentsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No payment of id: " + id));
		paymentsRepository.delete(payments);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
