package com.springboot.uber.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.springboot.uber.repository.PaymentsRepository;

@RestController
@RequestMapping("/api/v1/")
public class PaymentsController {

	@Autowired
	private PaymentsRepository paymentsRepository;

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

	@PutMapping("/payments/{id}")
	public ResponseEntity<Payments> updatePaymentsbyId(@PathVariable Long id, @RequestBody Payments paymentDetails) {
		Payments payments = paymentsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No payment of id: " + id));
		payments.setStatus(paymentDetails.getStatus());
		payments.setMethod(paymentDetails.getMethod());
		payments.setUser(paymentDetails.getUser());
		payments.setBooking(paymentDetails.getBooking());
		payments.setDriver(paymentDetails.getDriver());

		Payments updatedPayments = paymentsRepository.save(payments);
		return ResponseEntity.ok(updatedPayments);
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
