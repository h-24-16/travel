package com.travel.api.endpoint.rest.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
public class TravelDto {
    private Long id;
    private String name;
    private String departure;
    private String destination;
    private String description;
    private Integer duration;
    private LocalDate departureDate;
    private Double price;
    private String photo;
}