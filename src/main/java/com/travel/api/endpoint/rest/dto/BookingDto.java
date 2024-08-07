package com.travel.api.endpoint.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class BookingDto {
    private Long id;
    private Boolean isValidate;
    private Long travelId;
    private Long clientId;
}
