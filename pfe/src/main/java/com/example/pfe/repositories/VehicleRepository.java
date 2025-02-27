package com.example.pfe.repositories;

import com.example.pfe.models.Vehicle;
import com.example.pfe.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByRegistrationNumber(String registrationNumber);

    List<Vehicle> findByAssignedDriver(User driver);

    List<Vehicle> findByStatus(Vehicle.VehicleStatus status);
}