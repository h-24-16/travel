package com.travel.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    private Travel travel;
}