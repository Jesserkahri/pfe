package com.example.pfe.controllers;

import com.example.pfe.exceptions.ResourceNotFoundException;
import com.example.pfe.models.FuelConsumption;
import com.example.pfe.services.FuelConsumptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/fuel-consumption")
public class FuelConsumptionController {

    private final FuelConsumptionService fuelConsumptionService;

    // Constructor injection of FuelConsumptionService
    public FuelConsumptionController(FuelConsumptionService fuelConsumptionService) {
        this.fuelConsumptionService = fuelConsumptionService;
    }

    // Create a new fuel consumption record
    @PostMapping
    public ResponseEntity<FuelConsumption> createFuelConsumption(@RequestBody FuelConsumption fuelConsumption) {
        FuelConsumption createdFuelConsumption = fuelConsumptionService.createFuelConsumption(fuelConsumption);
        return new ResponseEntity<>(createdFuelConsumption, HttpStatus.CREATED);
    }

    // Get a specific fuel consumption record by its ID
    @GetMapping("/{id}")
    public ResponseEntity<FuelConsumption> getFuelConsumptionById(@PathVariable Long id) {
        try {
            FuelConsumption fuelConsumption = fuelConsumptionService.getFuelConsumptionById(id);
            return new ResponseEntity<>(fuelConsumption, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all fuel consumption records
    @GetMapping
    public ResponseEntity<List<FuelConsumption>> getAllFuelConsumptions() {
        List<FuelConsumption> fuelConsumptionList = fuelConsumptionService.getAllFuelConsumptions();
        return new ResponseEntity<>(fuelConsumptionList, HttpStatus.OK);
    }

    // Get fuel consumption records for a specific vehicle
    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<FuelConsumption>> getFuelConsumptionsByVehicle(@PathVariable Long vehicleId) {
        List<FuelConsumption> fuelConsumptionList = fuelConsumptionService.getFuelConsumptionsByVehicle(vehicleId);
        return new ResponseEntity<>(fuelConsumptionList, HttpStatus.OK);
    }

    // Get fuel consumption records for a specific equipment
    @GetMapping("/equipment/{equipmentId}")
    public ResponseEntity<List<FuelConsumption>> getFuelConsumptionsByEquipment(@PathVariable Long equipmentId) {
        List<FuelConsumption> fuelConsumptionList = fuelConsumptionService.getFuelConsumptionsByEquipment(equipmentId);
        return new ResponseEntity<>(fuelConsumptionList, HttpStatus.OK);
    }

    // Get fuel consumption records by a specific date range
    @GetMapping("/date-range")
    public ResponseEntity<List<FuelConsumption>> getFuelConsumptionsByDateRange(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        List<FuelConsumption> fuelConsumptionList = fuelConsumptionService.getFuelConsumptionsByDateRange(startDate,
                endDate);
        return new ResponseEntity<>(fuelConsumptionList, HttpStatus.OK);
    }

    // Update a fuel consumption record by its ID
    @PutMapping("/{id}")
    public ResponseEntity<FuelConsumption> updateFuelConsumption(@PathVariable Long id,
            @RequestBody FuelConsumption fuelConsumptionDetails) {
        try {
            FuelConsumption updatedFuelConsumption = fuelConsumptionService.updateFuelConsumption(id,
                    fuelConsumptionDetails);
            return new ResponseEntity<>(updatedFuelConsumption, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a fuel consumption record by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuelConsumption(@PathVariable Long id) {
        try {
            fuelConsumptionService.deleteFuelConsumption(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
