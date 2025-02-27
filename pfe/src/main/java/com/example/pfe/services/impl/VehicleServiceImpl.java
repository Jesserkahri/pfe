package com.example.pfe.services.impl;

import com.example.pfe.models.Vehicle;
import com.example.pfe.models.User;
import com.example.pfe.repositories.VehicleRepository;
import com.example.pfe.services.VehicleService;
import com.example.pfe.exceptions.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Override
    @Transactional
    public Vehicle registerVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    @Override
    public Optional<Vehicle> getVehicleByRegistrationNumber(String registrationNumber) {
        return vehicleRepository.findByRegistrationNumber(registrationNumber);
    }

    @Override
    public List<Vehicle> getVehiclesByDriver(User driver) {
        return vehicleRepository.findByAssignedDriver(driver);
    }

    @Override
    public List<Vehicle> getVehiclesByStatus(Vehicle.VehicleStatus status) {
        return vehicleRepository.findByStatus(status);
    }

    @Override
    @Transactional
    public Vehicle updateVehicle(Long id, Vehicle vehicleDetails) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + id));

        if (vehicleDetails.getRegistrationNumber() != null) {
            vehicle.setRegistrationNumber(vehicleDetails.getRegistrationNumber());
        }

        if (vehicleDetails.getAssignedDriver() != null) {
            vehicle.setAssignedDriver(vehicleDetails.getAssignedDriver());
        }

        if (vehicleDetails.getStatus() != null) {
            vehicle.setStatus(vehicleDetails.getStatus());
        }

        return vehicleRepository.save(vehicle);
    }

    @Override
    @Transactional
    public void deleteVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + id));
        vehicleRepository.delete(vehicle);
    }
}