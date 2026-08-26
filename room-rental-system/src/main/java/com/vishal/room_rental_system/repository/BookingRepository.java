package com.vishal.room_rental_system.repository;

import com.vishal.room_rental_system.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
