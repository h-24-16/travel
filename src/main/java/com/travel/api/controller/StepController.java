package com.travel.api.controller;

import com.travel.api.model.Step;
import com.travel.api.service.impl.StepService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/step")
public class StepController {
    private StepService stepService;

    @PutMapping
    public ResponseEntity<Step> crupdateStep(@RequestBody Step step) {
        return ResponseEntity.ok(stepService.crupdateStep(step));
    }
}