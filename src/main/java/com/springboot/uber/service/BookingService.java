package com.springboot.uber.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.uber.model.Booking;
import com.springboot.uber.model.Payments;
import com.springboot.uber.repository.BookingRepository;
import com.springboot.uber.repository.PaymentsRepository;

@Service
public class BookingService {
	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private PaymentsRepository paymentRepository;

	@PersistenceContext
	private EntityManager entityManager;

//	NOTE: needed for conflict serializable SQL Transactions
	@Transactional
	public void saveBookingAndPayment(Booking booking, Payments payment) {
		bookingRepository.save(booking);
		payment.setBooking(booking);
		paymentRepository.save(payment);
	}

//	NOTE: not needed since updation won't create conflicts 
	@Transactional
	public void updateBookingAndPayment(Booking booking, Payments payment) {
		entityManager.merge(booking);
		payment.setBooking(booking);
		entityManager.merge(payment);
//		bookingRepository.save(booking);
//		payment.setBooking(booking);
//		paymentRepository.save(payment);
	}

}
