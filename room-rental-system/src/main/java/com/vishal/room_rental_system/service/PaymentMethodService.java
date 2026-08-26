package com.vishal.room_rental_system.service;
import com.vishal.room_rental_system.entity.PaymentMethod;
import com.vishal.room_rental_system.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;


    public PaymentMethod savePaymentMethod(
            PaymentMethod paymentMethod) {

        return paymentMethodRepository.save(
                paymentMethod);
    }


    public List<PaymentMethod> getAllPaymentMethods() {

        return paymentMethodRepository.findAll();
    }


    public Optional<PaymentMethod> getPaymentMethodById(
            Long id) {

        return paymentMethodRepository.findById(id);
    }


    public PaymentMethod updatePaymentMethod(
            Long id,
            PaymentMethod paymentMethod) {

        PaymentMethod existing =
                paymentMethodRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Payment method not found"));

        existing.setName(
                paymentMethod.getName());

        existing.setStatus(
                paymentMethod.getStatus());

        return paymentMethodRepository.save(
                existing);
    }


    public void deletePaymentMethod(Long id) {

        if (!paymentMethodRepository.existsById(id)) {

            throw new RuntimeException(
                    "Payment method not found");
        }

        paymentMethodRepository.deleteById(id);
    }
}