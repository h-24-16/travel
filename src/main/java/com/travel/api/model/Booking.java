package com.travel.api.model;

import com.travel.api.model.auth.Client;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isValidate;

    @ManyToOne
    private Travel travel;

    @ManyToOne
    private Client client;
}