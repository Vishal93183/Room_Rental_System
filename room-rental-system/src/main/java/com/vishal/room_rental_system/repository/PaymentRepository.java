package com.vishal.room_rental_system.repository;

import com.vishal.room_rental_system.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
