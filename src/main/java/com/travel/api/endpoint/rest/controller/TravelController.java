package com.travel.api.endpoint.rest.controller;

import com.travel.api.endpoint.rest.dto.TravelDto;
import com.travel.api.endpoint.rest.mapper.TravelMapper;
import com.travel.api.service.impl.TravelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/travels")
@Tag(name = "travel")
public class TravelController {
    private TravelService travelService;
    private TravelMapper travelMapper;

    @PutMapping
    public ResponseEntity<TravelDto> crupdateTravel(@RequestBody TravelDto travel) {
        return ResponseEntity.ok(travelMapper.toDto(travelService.crupdateTravel(travelMapper.toEntity(travel))));
    }
}