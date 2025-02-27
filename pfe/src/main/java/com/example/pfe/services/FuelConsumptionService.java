package com.example.pfe.services;

import java.time.LocalDateTime;
import java.util.List;

import com.example.pfe.models.FuelConsumption;

public interface FuelConsumptionService {
    FuelConsumption getFuelConsumptionById(Long id);

    List<FuelConsumption> getAllFuelConsumptions();

    List<FuelConsumption> getFuelConsumptionsByVehicle(Long vehicleId);

    List<FuelConsumption> getFuelConsumptionsByEquipment(Long equipmentId);

    List<FuelConsumption> getFuelConsumptionsByDateRange(LocalDateTime startDate, LocalDateTime endDate);

    FuelConsumption createFuelConsumption(FuelConsumption fuelConsumption);

    FuelConsumption updateFuelConsumption(Long id, FuelConsumption fuelConsumptionDetails);

    void deleteFuelConsumption(Long id);

}
