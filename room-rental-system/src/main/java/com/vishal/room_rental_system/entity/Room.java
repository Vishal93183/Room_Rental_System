package com.vishal.room_rental_system.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String roomNumber;

    @Column(nullable = false)
    private String roomType;

    @Column(nullable = false)
    private Double price;

    private String status = "AVAILABLE";

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "room")
    private List<Booking> bookings;
    // Getters and Setters
}
