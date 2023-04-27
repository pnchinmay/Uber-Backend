package com.springboot.uber.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.uber.exception.ResourceNotFoundException;
import com.springboot.uber.model.Booking;
import com.springboot.uber.model.Payments;
import com.springboot.uber.repository.BookingRepository;
import com.springboot.uber.service.BookingService;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*")
public class BookingController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping("/bookings")
	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

	@PostMapping("/bookings")
	public Booking createBooking(@RequestBody Booking booking) {
		return bookingRepository.save(booking);
	}

//	@PostMapping("/bookings/bookings&payments")
//	public ResponseEntity<Booking> createBookingAndPayment(@RequestBody Booking booking,
//			@RequestBody Payments payment) {
//		bookingService.saveBookingAndPayment(booking, payment);
//		return ResponseEntity.ok(booking);
//	}

	@PostMapping("/bookings/bookings&payments")
	public ResponseEntity<Booking> createBookingAndPayment(@RequestBody Map<String, Object> request) {
		Booking booking = objectMapper.convertValue(request.get("booking"), Booking.class);
		Payments payment = objectMapper.convertValue(request.get("payment"), Payments.class);
		bookingService.saveBookingAndPayment(booking, payment);
		return ResponseEntity.ok(booking);
	}

	@GetMapping("/bookings/{id}")
	public ResponseEntity<Booking> getBookingbyId(@PathVariable Long id) {
		Booking booking = bookingRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No booking of id: " + id));
		return ResponseEntity.ok(booking);
	}

	// Non-conflict Serializable SQL Transactions
	@PutMapping("/bookings/bookings&payments")
	public ResponseEntity<Booking> updateBookingAndPayment(@RequestBody Map<String, Object> request) {
		Booking booking = objectMapper.convertValue(request.get("booking"), Booking.class);
		Payments payment = objectMapper.convertValue(request.get("payment"), Payments.class);
		bookingService.updateBookingAndPayment(booking, payment);
		return ResponseEntity.ok(booking);
	}

	@PutMapping("/bookings/{id}")
	public ResponseEntity<Booking> updateBookingById(@PathVariable Long id, @RequestBody Booking bookingDetails) {
		logger.warn("Request Body: " + bookingDetails.toString()); // add this line to print the request body
		int status = bookingDetails.getStatus();
		String bookingTime = bookingDetails.getBookingTime();
		String startTime = bookingDetails.getStartTime();
		String endTime = bookingDetails.getEndTime();
		Long ride_id = bookingDetails.getRide().getId();

		bookingRepository.update(id, status, bookingTime, startTime, endTime, ride_id);

		Booking booking = bookingRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No payment of id: " + id));
		return ResponseEntity.ok(booking);
	}

	@DeleteMapping("/bookings/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteBooking(@PathVariable Long id) {
		Booking booking = bookingRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No booking of id: " + id));
		bookingRepository.delete(booking);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}