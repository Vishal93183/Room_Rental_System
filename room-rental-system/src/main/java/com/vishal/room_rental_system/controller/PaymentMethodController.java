package com.vishal.room_rental_system.controller;


import com.vishal.room_rental_system.entity.PaymentMethod;
import com.vishal.room_rental_system.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment-methods")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;


    @PostMapping
    public ResponseEntity<PaymentMethod> savePaymentMethod(
            @RequestBody PaymentMethod paymentMethod) {

        return ResponseEntity.ok(
                paymentMethodService
                        .savePaymentMethod(paymentMethod));
    }


    @GetMapping
    public ResponseEntity<List<PaymentMethod>>
    getAllPaymentMethods() {

        return ResponseEntity.ok(
                paymentMethodService
                        .getAllPaymentMethods());
    }


    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethod>
    getPaymentMethodById(
            @PathVariable Long id) {

        return paymentMethodService
                .getPaymentMethodById(id)
                .map(ResponseEntity::ok)
                .orElse(
                        ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethod>
    updatePaymentMethod(
            @PathVariable Long id,
            @RequestBody PaymentMethod paymentMethod) {

        return ResponseEntity.ok(
                paymentMethodService
                        .updatePaymentMethod(
                                id,
                                paymentMethod));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    deletePaymentMethod(
            @PathVariable Long id) {

        paymentMethodService
                .deletePaymentMethod(id);

        return ResponseEntity.ok(
                "Payment method deleted successfully");
    }
}