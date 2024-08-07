package com.travel.api.controller;

import com.travel.api.model.Booking;
import com.travel.api.service.impl.BookingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/booking")
@Tag(name = "booking")
public class BookingController {
    private BookingService bookingService;

    @GetMapping("/total/{bookingId}")
    public ResponseEntity<Double> totalCost(@PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingService.bookingTotalCost(bookingId));
    }

    @PutMapping
    public ResponseEntity<Booking> crupdateBooking(@RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.crupdateBooking(booking));
    }

    @PutMapping("/validation/{clientId}")
    public ResponseEntity<Booking> validateBooking(@PathVariable Long clientId) {
        return ResponseEntity.ok(bookingService.validateBooking(clientId));
    }
}