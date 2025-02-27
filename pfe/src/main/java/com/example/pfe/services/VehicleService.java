package com.example.pfe.services;

import com.example.pfe.models.Vehicle;
import com.example.pfe.models.User;
import java.util.List;
import java.util.Optional;

public interface VehicleService {
    Vehicle registerVehicle(Vehicle vehicle);

    Optional<Vehicle> getVehicleById(Long id);

    Optional<Vehicle> getVehicleByRegistrationNumber(String registrationNumber);

    List<Vehicle> getVehiclesByDriver(User driver);

    List<Vehicle> getVehiclesByStatus(Vehicle.VehicleStatus status);

    Vehicle updateVehicle(Long id, Vehicle vehicleDetails);

    void deleteVehicle(Long id);
}