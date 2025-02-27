package com.example.pfe.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "fuel_stock")
@Getter
@Setter
@NoArgsConstructor
public class FuelStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String location; // Depot, internal station, etc.

    @Column(nullable = false)
    private Double currentQuantity; // Amount of fuel available

    @Column(nullable = false)
    private Double capacity; // Maximum storage capacity

    @Column(nullable = false)
    private Double minimumThreshold; // Alerts when fuel goes below this level
}
