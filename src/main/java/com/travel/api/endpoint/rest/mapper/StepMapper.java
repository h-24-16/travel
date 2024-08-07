package com.travel.api.endpoint.rest.mapper;

import com.travel.api.endpoint.rest.dto.StepDto;
import com.travel.api.model.Step;
import com.travel.api.service.impl.StepService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StepMapper {
    private final StepService stepService;

    public StepDto toDto(Step step) {
        return StepDto.builder()
                .id(step.getId())
                .name(step.getName())
                .description(step.getDescription())
                .travelId(step.getTravel().getId())
                .build();
    }

    public Step toEntity(StepDto step) {
        return Step.builder()
                .id(step.getId())
                .name(step.getName())
                .description(step.getDescription())
                .travel(stepService.getStepById(step.getId()).getTravel())
                .build();
    }

}