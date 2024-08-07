package com.travel.api.endpoint.rest.controller;

import com.travel.api.endpoint.rest.dto.BookingDto;
import com.travel.api.endpoint.rest.mapper.BookingMapper;
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
    private BookingMapper bookingMapper;

    @GetMapping("/total/{bookingId}")
    public ResponseEntity<Double> totalCost(@PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingService.bookingTotalCost(bookingId));
    }

    @PutMapping
    public ResponseEntity<BookingDto> crupdateBooking(@RequestBody BookingDto booking) {
        return ResponseEntity.ok(bookingMapper.toDto(bookingService.crupdateBooking(bookingMapper.toEntity(booking))));
    }

    @PutMapping("/validation/{clientId}")
    public ResponseEntity<BookingDto> validateBooking(@PathVariable Long clientId) {
        return ResponseEntity.ok(bookingMapper.toDto(bookingService.validateBooking(clientId)));
    }
}