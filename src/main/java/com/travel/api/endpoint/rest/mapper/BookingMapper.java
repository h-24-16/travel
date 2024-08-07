package com.travel.api.endpoint.rest.mapper;


import com.travel.api.endpoint.rest.dto.BookingDto;
import com.travel.api.model.Booking;
import com.travel.api.repository.ClientRepository;
import com.travel.api.service.impl.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class BookingMapper {
    private final ClientRepository clientRepository;
    private final BookingService service;

    public BookingDto toDto(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .clientId(booking.getClient().getId())
                .isValidate(booking.getIsValidate())
                .travelId(booking.getTravel().getId())
                .build();
    }

    public Booking toEntity(BookingDto booking) {
        Booking entity = service.getBookingById(booking.getId());

        return Booking.builder()
                .id(booking.getId())
                .client(Objects.nonNull(entity) ? entity.getClient() : null)
                .travel(Objects.nonNull(entity) ? entity.getTravel() : null)
                .isValidate(booking.getIsValidate())
                .build();
    }
}
