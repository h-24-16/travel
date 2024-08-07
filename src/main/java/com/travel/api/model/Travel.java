package com.travel.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String departure;
    private String destination;
    private Integer duration;
    private Double price;

    @ManyToOne
    private FileDB photo;

}