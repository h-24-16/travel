package com.travel.api.service.impl;

import com.travel.api.model.Step;
import com.travel.api.repository.StepRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StepService {
    private StepRepository stepRepository;

    public Step crupdateStep(Step step) {
        return stepRepository.save(step);
    }

    public Step getStepById(Long id) {
        return stepRepository.findById(id).get();
    }
}