package com.travel.api.service.impl;

import com.travel.api.model.Booking;
import com.travel.api.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {
    private BookingRepository bookingRepository;

    public Double bookingTotalCost(Long id) {
        return bookingRepository.findById(id).get().getTotalCost();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.getById(id);
    }

    public Booking crupdateBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking validateBooking(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        booking.ifPresent(value -> value.setIsValidate(true));
        return bookingRepository.save(booking.get());
    }
}