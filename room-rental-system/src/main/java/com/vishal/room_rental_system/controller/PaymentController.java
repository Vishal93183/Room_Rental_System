package com.vishal.room_rental_system.controller;


import com.vishal.room_rental_system.entity.Payment;
import com.vishal.room_rental_system.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    // Create Payment
    @PostMapping
    public ResponseEntity<Payment> savePayment(

            @RequestParam Long bookingId,

            @RequestParam Long paymentMethodId,

            @RequestBody Payment payment) {

        Payment savedPayment =
                paymentService.savePayment(
                        bookingId,
                        paymentMethodId,
                        payment);

        return ResponseEntity.ok(savedPayment);
    }


    // Get All
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {

        return ResponseEntity.ok(
                paymentService.getAllPayments());
    }


    // Get By ID
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(
            @PathVariable Long id) {

        return paymentService
                .getPaymentById(id)
                .map(ResponseEntity::ok)
                .orElse(
                        ResponseEntity.notFound().build());
    }


    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(
            @PathVariable Long id,
            @RequestBody Payment payment) {

        return ResponseEntity.ok(
                paymentService.updatePayment(
                        id,
                        payment));
    }


    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayment(
            @PathVariable Long id) {

        paymentService.deletePayment(id);

        return ResponseEntity.ok(
                "Payment deleted successfully");
    }
}