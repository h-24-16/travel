package com.travel.api.endpoint.rest.controller;

import com.travel.api.endpoint.rest.dto.StepDto;
import com.travel.api.endpoint.rest.mapper.StepMapper;
import com.travel.api.model.Step;
import com.travel.api.service.impl.StepService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/step")
@Tag(name = "step")
public class StepController {
    private StepService stepService;
    private StepMapper stepMapper;

    @PutMapping
    public ResponseEntity<StepDto> crupdateStep(@RequestBody StepDto stepDto) {
        return ResponseEntity.ok(stepMapper.toDto(stepService.crupdateStep(stepMapper.toEntity(stepDto))));
    }
}