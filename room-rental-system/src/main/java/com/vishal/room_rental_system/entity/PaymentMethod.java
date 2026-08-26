package com.vishal.room_rental_system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter

@Entity
@Table(name = "payment_methods")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private Boolean status = true;

    @OneToMany(mappedBy = "paymentMethod")
    private List<Payment> payments;

    @OneToMany(mappedBy = "paymentMethod")
    private List<Booking> bookings;
    // Getters and Setters
}