package com.travel.api.endpoint.rest.mapper;

import com.travel.api.endpoint.rest.dto.TravelDto;
import com.travel.api.model.Travel;
import com.travel.api.service.impl.FileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TravelMapper {
    private final FileService fileService;

    public TravelDto toDto(Travel travel) {
        return TravelDto.builder()
                .id(travel.getId())
                .name(travel.getName())
                .departure(travel.getDeparture())
                .destination(travel.getDestination())
                .description(travel.getDescription())
                .duration(travel.getDuration())
                .departureDate(travel.getDepartureDate())
                .price(travel.getPrice())
                .photo(travel.getPhoto().getPath())
                .build();
    }

    public Travel toEntity(TravelDto travelDto) {
        return Travel.builder()
                .id(travelDto.getId())
                .name(travelDto.getName())
                .departure(travelDto.getDeparture())
                .destination(travelDto.getDestination())
                .description(travelDto.getDescription())
                .duration(travelDto.getDuration())
                .departureDate(travelDto.getDepartureDate())
                .price(travelDto.getPrice())
                .photo(fileService.getFileByPath(travelDto.getPhoto()))
                .build();
    }

}