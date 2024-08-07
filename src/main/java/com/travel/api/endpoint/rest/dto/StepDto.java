package com.travel.api.endpoint.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class StepDto {
    private Long id;
    private String name;
    private String description;
    private Long travelId;
}