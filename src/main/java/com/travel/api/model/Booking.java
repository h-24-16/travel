package com.travel.api.model;

import com.travel.api.model.auth.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isValidate;
    private Integer clientNumber;

    @ManyToOne
    private Travel travel;

    @ManyToOne
    private Client client;

    @Transient
    private Double totalCost;

    public Double getTotalCost() {
        if (travel != null && clientNumber != null) {
            return clientNumber * travel.getPrice();
        }
        return 0.0;
    }
}
