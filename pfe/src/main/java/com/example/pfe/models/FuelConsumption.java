package com.example.pfe.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "fuel_consumption")
@Getter
@Setter
@NoArgsConstructor
public class FuelConsumption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = true)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "equipment_id", nullable = true)
    private Equipment equipment;

    @Column(nullable = false)
    private Double quantity;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private String location;

    @ManyToOne
    @JoinColumn(name = "recorded_by", nullable = false)
    private User recordedBy;

    @PrePersist
    protected void onCreate() {
        this.date = LocalDateTime.now();
    }
}
