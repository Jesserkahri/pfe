package com.example.pfe.services.impl;

import com.example.pfe.models.FuelStock;
import com.example.pfe.repositories.FuelStockRepository;
import com.example.pfe.services.FuelStockService;
import com.example.pfe.exceptions.ResourceNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuelStockServiceImpl implements FuelStockService {

    private final FuelStockRepository fuelStockRepository;

    public FuelStockServiceImpl(FuelStockRepository fuelStockRepository) {
        this.fuelStockRepository = fuelStockRepository;
    }

    @Override
    public List<FuelStock> getAllFuelStocks() {
        return fuelStockRepository.findAll();
    }

    @Override
    public FuelStock getFuelStockById(Long id) {
        return fuelStockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fuel stock not found with id: " + id));
    }

    @Override
    public FuelStock createFuelStock(FuelStock fuelStock) {
        return fuelStockRepository.save(fuelStock);
    }

    @Override
    public FuelStock updateFuelStock(Long id, FuelStock updatedFuelStock) {
        FuelStock existingFuelStock = getFuelStockById(id);

        // Update only non-null fields
        if (updatedFuelStock.getLocation() != null
                && !updatedFuelStock.getLocation().equals(existingFuelStock.getLocation())) {
            existingFuelStock.setLocation(updatedFuelStock.getLocation());
        }

        if (updatedFuelStock.getCurrentQuantity() != null
                && !updatedFuelStock.getCurrentQuantity().equals(existingFuelStock.getCurrentQuantity())) {
            existingFuelStock.setCurrentQuantity(updatedFuelStock.getCurrentQuantity());
        }

        if (updatedFuelStock.getCapacity() != null
                && !updatedFuelStock.getCapacity().equals(existingFuelStock.getCapacity())) {
            existingFuelStock.setCapacity(updatedFuelStock.getCapacity());
        }

        if (updatedFuelStock.getMinimumThreshold() != null
                && !updatedFuelStock.getMinimumThreshold().equals(existingFuelStock.getMinimumThreshold())) {
            existingFuelStock.setMinimumThreshold(updatedFuelStock.getMinimumThreshold());
        }

        return fuelStockRepository.save(existingFuelStock);
    }

    @Override
    public void deleteFuelStock(Long id) {
        FuelStock fuelStock = getFuelStockById(id);
        fuelStockRepository.delete(fuelStock);
    }

    @Override
    public boolean isFuelBelowThreshold(Long id) {
        FuelStock fuelStock = getFuelStockById(id);
        return fuelStock.getCurrentQuantity() < fuelStock.getMinimumThreshold();
    }

    @Override
    public FuelStock updateFuelQuantity(Long id, Double quantity) {
        FuelStock fuelStock = getFuelStockById(id);

        // Add or subtract quantity from current fuel stock
        fuelStock.setCurrentQuantity(fuelStock.getCurrentQuantity() + quantity);

        return fuelStockRepository.save(fuelStock);
    }
}
