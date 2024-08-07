package com.travel.api.repository.rest;

import com.travel.api.model.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(collectionResourceRel = "travel", path = "travel")
public interface TravelRestRepository extends JpaRepository<Travel, Long> {
    List<Travel> findTravelByName(@Param("name") String name);
}