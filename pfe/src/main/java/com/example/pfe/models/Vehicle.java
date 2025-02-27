package com.example.pfe.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String registrationNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType type;

    @Column(nullable = false)
    private Double fuelCapacity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleStatus status;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = true)
    private User assignedDriver;

    @Column
    private LocalDateTime lastMaintenanceDate;

    public enum VehicleType {
        CAR, TRUCK, BUS, MOTORCYCLE, HEAVY_VEHICLE
    }

    public enum VehicleStatus {
        ACTIVE, INACTIVE, MAINTENANCE
    }

    @PrePersist
    protected void onCreate() {
        this.lastMaintenanceDate = LocalDateTime.now();
    }
}
