package com.vishal.room_rental_system.repository;

import com.vishal.room_rental_system.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

}