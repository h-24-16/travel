package com.travel.api.repository.rest;

import com.travel.api.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface BookingRestRepository extends JpaRepository<Booking, Long> {
    List<Booking> findBookingByClientId(@Param("clientId") Long clientId);
}