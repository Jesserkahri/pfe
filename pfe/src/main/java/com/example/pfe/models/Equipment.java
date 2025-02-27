package com.example.pfe.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "equipment")
@Getter
@Setter
@NoArgsConstructor
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EquipmentType type;

    @Column(nullable = false)
    private Double fuelCapacity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EquipmentStatus status;

    public enum EquipmentType {
        GENERATOR, PUMP, EXCAVATOR, CRANE, CONSTRUCTION_MACHINE
    }

    public enum EquipmentStatus {
        OPERATIONAL, NON_OPERATIONAL, UNDER_REPAIR
    }
}
