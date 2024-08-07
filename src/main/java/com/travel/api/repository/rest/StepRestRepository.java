package com.travel.api.repository.rest;

import com.travel.api.model.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface StepRestRepository extends JpaRepository<Step, Long> {
    List<Step> findStepByTravelId(@Param("travelId") Long travelId);
}