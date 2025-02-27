package com.example.pfe.services;

import com.example.pfe.models.FuelStock;

import java.util.List;

public interface FuelStockService {
    List<FuelStock> getAllFuelStocks();

    FuelStock getFuelStockById(Long id);

    FuelStock createFuelStock(FuelStock fuelStock);

    FuelStock updateFuelStock(Long id, FuelStock updatedFuelStock);

    void deleteFuelStock(Long id);

    boolean isFuelBelowThreshold(Long id);

    FuelStock updateFuelQuantity(Long id, Double quantity);
}
