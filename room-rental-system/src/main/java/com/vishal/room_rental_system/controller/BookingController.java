package com.vishal.room_rental_system.controller;


import com.vishal.room_rental_system.entity.Booking;
import com.vishal.room_rental_system.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;


    // Create Booking
    @PostMapping
    public ResponseEntity<Booking> saveBooking(

            @RequestParam Long userId,

            @RequestParam Long roomId,

            @RequestParam Long paymentMethodId,

            @RequestBody Booking booking) {

        Booking savedBooking =
                bookingService.saveBooking(
                        userId,
                        roomId,
                        paymentMethodId,
                        booking);

        return ResponseEntity.ok(savedBooking);
    }


    // Get All
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {

        return ResponseEntity.ok(
                bookingService.getAllBookings());
    }


    // Get By ID
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(
            @PathVariable Long id) {

        return bookingService
                .getBookingById(id)
                .map(ResponseEntity::ok)
                .orElse(
                        ResponseEntity.notFound().build());
    }


    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(
            @PathVariable Long id,
            @RequestBody Booking booking) {

        return ResponseEntity.ok(
                bookingService.updateBooking(
                        id,
                        booking));
    }


    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(
            @PathVariable Long id) {

        bookingService.deleteBooking(id);

        return ResponseEntity.ok(
                "Booking deleted successfully");
    }
}