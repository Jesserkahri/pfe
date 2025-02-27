package com.example.pfe.services;

import com.example.pfe.models.FuelSupply;
import com.example.pfe.models.FuelStock;
import com.example.pfe.models.User;

import java.time.LocalDateTime;
import java.util.List;

public interface FuelSupplyService {
    List<FuelSupply> getAllFuelSupplies();

    FuelSupply getFuelSupplyById(Long id);

    List<FuelSupply> getFuelSuppliesBySupplier(String supplierName);

    List<FuelSupply> getFuelSuppliesByDateRange(LocalDateTime startDate, LocalDateTime endDate);

    Double getTotalSuppliedBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<FuelSupply> getFuelSuppliesByFuelStock(FuelStock fuelStock);

    List<FuelSupply> getFuelSuppliesByUser(User user);

    FuelSupply addFuelSupply(FuelSupply fuelSupply);
}
