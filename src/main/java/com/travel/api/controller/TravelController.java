package com.travel.api.controller;

import com.travel.api.model.Travel;
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

    @PutMapping
    public ResponseEntity<Travel> crupdateTravel(@RequestBody Travel travel) {
        return ResponseEntity.ok(travelService.crupdateTravel(travel));
    }
}