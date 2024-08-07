package com.travel.api.service.impl;

import com.travel.api.model.Travel;
import com.travel.api.repository.TravelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TravelService {
    private TravelRepository travelRepository;

    public Travel crupdateTravel(Travel travel) {
        return travelRepository.save(travel);
    }
}