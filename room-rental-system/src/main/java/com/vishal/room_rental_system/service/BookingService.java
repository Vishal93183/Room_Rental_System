package com.vishal.room_rental_system.service;

import com.vishal.room_rental_system.entity.Booking;
import com.vishal.room_rental_system.entity.PaymentMethod;
import com.vishal.room_rental_system.entity.Room;
import com.vishal.room_rental_system.entity.User;
import com.vishal.room_rental_system.repository.BookingRepository;
import com.vishal.room_rental_system.repository.PaymentMethodRepository;
import com.vishal.room_rental_system.repository.RoomRepository;
import com.vishal.room_rental_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;


    // Create Booking
    public Booking saveBooking(
            Long userId,
            Long roomId,
            Long paymentMethodId,
            Booking booking) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() ->
                        new RuntimeException("Room not found"));

        PaymentMethod paymentMethod =
                paymentMethodRepository.findById(paymentMethodId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Payment method not found"));

        booking.setUser(user);
        booking.setRoom(room);
        booking.setPaymentMethod(paymentMethod);

        // Booking should initially be pending
        booking.setBookingStatus("PENDING");

        return bookingRepository.save(booking);
    }


    // Get All Bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }


    // Get Booking By ID
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }


    // Update Booking
    public Booking updateBooking(
            Long id,
            Booking booking) {

        Booking existingBooking =
                bookingRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Booking not found"));

        existingBooking.setCheckIn(
                booking.getCheckIn());

        existingBooking.setCheckOut(
                booking.getCheckOut());

        existingBooking.setBookingStatus(
                booking.getBookingStatus());

        existingBooking.setTotalAmount(
                booking.getTotalAmount());

        return bookingRepository.save(existingBooking);
    }


    // Delete Booking
    public void deleteBooking(Long id) {

        if (!bookingRepository.existsById(id)) {
            throw new RuntimeException(
                    "Booking not found");
        }

        bookingRepository.deleteById(id);
    }
}