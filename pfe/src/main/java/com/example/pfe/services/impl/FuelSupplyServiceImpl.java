package com.example.pfe.services.impl;

import com.example.pfe.models.FuelSupply;
import com.example.pfe.models.FuelStock;
import com.example.pfe.models.User;
import com.example.pfe.repositories.FuelSupplyRepository;
import com.example.pfe.services.FuelSupplyService;
import com.example.pfe.exceptions.ResourceNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FuelSupplyServiceImpl implements FuelSupplyService {

    private final FuelSupplyRepository fuelSupplyRepository;

    public FuelSupplyServiceImpl(FuelSupplyRepository fuelSupplyRepository) {
        this.fuelSupplyRepository = fuelSupplyRepository;
    }

    @Override
    public List<FuelSupply> getAllFuelSupplies() {
        return fuelSupplyRepository.findAll();
    }

    @Override
    public FuelSupply getFuelSupplyById(Long id) {
        return fuelSupplyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fuel supply not found with id: " + id));
    }

    @Override
    public List<FuelSupply> getFuelSuppliesBySupplier(String supplierName) {
        return fuelSupplyRepository.findBySupplierName(supplierName);
    }

    @Override
    public List<FuelSupply> getFuelSuppliesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return fuelSupplyRepository.findByDateRange(startDate, endDate);
    }

    @Override
    public Double getTotalSuppliedBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return fuelSupplyRepository.getTotalSuppliedBetween(startDate, endDate);
    }

    @Override
    public List<FuelSupply> getFuelSuppliesByFuelStock(FuelStock fuelStock) {
        return fuelSupplyRepository.findByFuelStock(fuelStock);
    }

    @Override
    public List<FuelSupply> getFuelSuppliesByUser(User user) {
        return fuelSupplyRepository.findByReceivedBy(user);
    }

    @Override
    public FuelSupply addFuelSupply(FuelSupply fuelSupply) {
        return fuelSupplyRepository.save(fuelSupply);
    }
}
