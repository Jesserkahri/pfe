package com.example.pfe.services.impl;

import com.example.pfe.models.FuelConsumption;
import com.example.pfe.repositories.FuelConsumptionRepository;
import com.example.pfe.services.FuelConsumptionService;
import com.example.pfe.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FuelConsumptionServiceImpl implements FuelConsumptionService {

    private final FuelConsumptionRepository fuelConsumptionRepository;

    @Override
    public FuelConsumption getFuelConsumptionById(Long id) {
        return fuelConsumptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fuel consumption record not found with id: " + id));
    }

    @Override
    public List<FuelConsumption> getAllFuelConsumptions() {
        return fuelConsumptionRepository.findAll();
    }

    @Override
    public List<FuelConsumption> getFuelConsumptionsByVehicle(Long vehicleId) {
        return fuelConsumptionRepository.findByVehicleId(vehicleId);
    }

    @Override
    public List<FuelConsumption> getFuelConsumptionsByEquipment(Long equipmentId) {
        return fuelConsumptionRepository.findByEquipmentId(equipmentId);
    }

    @Override
    public List<FuelConsumption> getFuelConsumptionsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return fuelConsumptionRepository.findBetweenDates(startDate, endDate);
    }

    @Override
    public FuelConsumption createFuelConsumption(FuelConsumption fuelConsumption) {
        return fuelConsumptionRepository.save(fuelConsumption);
    }

    @Override
    public FuelConsumption updateFuelConsumption(Long id, FuelConsumption fuelConsumptionDetails) {
        FuelConsumption fuelConsumption = getFuelConsumptionById(id);

        // Update fields only if necessary
        if (fuelConsumptionDetails.getQuantity() != null &&
                !fuelConsumptionDetails.getQuantity().equals(fuelConsumption.getQuantity())) {
            fuelConsumption.setQuantity(fuelConsumptionDetails.getQuantity());
        }

        if (fuelConsumptionDetails.getLocation() != null &&
                !fuelConsumptionDetails.getLocation().equals(fuelConsumption.getLocation())) {
            fuelConsumption.setLocation(fuelConsumptionDetails.getLocation());
        }

        // Update other fields similarly...

        return fuelConsumptionRepository.save(fuelConsumption);
    }

    @Override
    public void deleteFuelConsumption(Long id) {
        FuelConsumption fuelConsumption = getFuelConsumptionById(id);
        fuelConsumptionRepository.delete(fuelConsumption);
    }
}