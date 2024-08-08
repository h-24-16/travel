package com.travel.api.endpoint.rest.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@Tag(name = "health")
public class HealthController {

    @GetMapping("/ping}")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }
}