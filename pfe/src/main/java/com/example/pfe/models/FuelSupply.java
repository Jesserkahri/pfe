package com.example.pfe.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "fuel_supply")
@Getter
@Setter
@NoArgsConstructor
public class FuelSupply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fuel_stock_id", nullable = false)
    private FuelStock fuelStock; // The stock being supplied

    @Column(nullable = false)
    private Double quantity; // Amount of fuel delivered

    @Column(nullable = false)
    private LocalDateTime deliveryDate;

    @Column(nullable = false)
    private String supplierName; // Supplier company or provider

    @ManyToOne
    @JoinColumn(name = "received_by", nullable = false)
    private User receivedBy; // The user who received the supply

    @PrePersist
    protected void onCreate() {
        this.deliveryDate = LocalDateTime.now();
    }
}
