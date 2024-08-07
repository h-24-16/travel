package com.travel.api.repository.rest;

import com.travel.api.model.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "travel", path = "travel")
public interface TravelRestRepository extends JpaRepository<Travel, Long> {
    List<Travel> findTravelByName(@Param("name") String name);
    List<Travel> findTravelByDestination(@Param("destination") String destination);
    List<Travel> findTravelById(@Param("travelId") Long travelId);
}