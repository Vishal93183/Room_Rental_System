package com.vishal.room_rental_system.service;


import com.vishal.room_rental_system.entity.Booking;
import com.vishal.room_rental_system.entity.Payment;
import com.vishal.room_rental_system.entity.PaymentMethod;
import com.vishal.room_rental_system.repository.BookingRepository;
import com.vishal.room_rental_system.repository.PaymentMethodRepository;
import com.vishal.room_rental_system.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;


    // Create Payment
    public Payment savePayment(
            Long bookingId,
            Long paymentMethodId,
            Payment payment) {

        Booking booking =
                bookingRepository.findById(bookingId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Booking not found"));

        PaymentMethod paymentMethod =
                paymentMethodRepository.findById(
                                paymentMethodId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Payment method not found"));

        payment.setBooking(booking);
        payment.setPaymentMethod(paymentMethod);

        if (payment.getPaidAt() == null) {
            payment.setPaidAt(
                    LocalDateTime.now());
        }

        Payment savedPayment =
                paymentRepository.save(payment);


        // Payment Success
        if ("SUCCESS".equalsIgnoreCase(
                payment.getPaymentStatus())) {

            booking.setBookingStatus("CONFIRMED");

            bookingRepository.save(booking);
        }

        return savedPayment;
    }


    // Get All Payments
    public List<Payment> getAllPayments() {

        return paymentRepository.findAll();
    }


    // Get Payment By ID
    public Optional<Payment> getPaymentById(
            Long id) {

        return paymentRepository.findById(id);
    }


    // Update Payment
    public Payment updatePayment(
            Long id,
            Payment payment) {

        Payment existingPayment =
                paymentRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Payment not found"));

        existingPayment.setAmount(
                payment.getAmount());

        existingPayment.setTransactionId(
                payment.getTransactionId());

        existingPayment.setPaymentStatus(
                payment.getPaymentStatus());

        existingPayment.setPaidAt(
                payment.getPaidAt());

        Payment updatedPayment =
                paymentRepository.save(
                        existingPayment);


        // Confirm Booking
        if ("SUCCESS".equalsIgnoreCase(
                updatedPayment.getPaymentStatus())) {

            Booking booking =
                    existingPayment.getBooking();

            booking.setBookingStatus(
                    "CONFIRMED");

            bookingRepository.save(booking);
        }

        return updatedPayment;
    }


    // Delete Payment
    public void deletePayment(Long id) {

        if (!paymentRepository.existsById(id)) {

            throw new RuntimeException(
                    "Payment not found");
        }

        paymentRepository.deleteById(id);
    }
}