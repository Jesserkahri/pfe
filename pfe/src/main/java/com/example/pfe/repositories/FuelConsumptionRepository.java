package com.example.pfe.repositories;

import com.example.pfe.models.FuelConsumption;
import com.example.pfe.models.Vehicle;
import com.example.pfe.models.Equipment;
import com.example.pfe.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FuelConsumptionRepository extends JpaRepository<FuelConsumption, Long> {
    List<FuelConsumption> findByVehicle(Vehicle vehicle);

    List<FuelConsumption> findByEquipment(Equipment equipment);

    List<FuelConsumption> findByRecordedBy(User user);

    @Query("SELECT fc FROM FuelConsumption fc WHERE fc.date BETWEEN :startDate AND :endDate")
    List<FuelConsumption> findBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT SUM(fc.quantity) FROM FuelConsumption fc WHERE fc.vehicle = :vehicle")
    Double getTotalConsumptionByVehicle(Vehicle vehicle);

    List<FuelConsumption> findByVehicleId(Long vehicleId);

    List<FuelConsumption> findByEquipmentId(Long equipmentId);
}