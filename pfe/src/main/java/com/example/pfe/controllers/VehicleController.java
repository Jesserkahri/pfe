package com.example.pfe.controllers;

import com.example.pfe.models.Vehicle;
import com.example.pfe.models.User;
import com.example.pfe.services.VehicleService;
import com.example.pfe.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    // Constructor injection of VehicleService
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // Register a new vehicle
    @PostMapping("/register")
    public ResponseEntity<Vehicle> registerVehicle(@RequestBody Vehicle vehicle) {
        Vehicle createdVehicle = vehicleService.registerVehicle(vehicle);
        return new ResponseEntity<>(createdVehicle, HttpStatus.CREATED);
    }

    // Get a vehicle by ID
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id)
                .map(vehicle -> new ResponseEntity<>(vehicle, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Return 404 if not found
    }

    // Get a vehicle by registration number
    @GetMapping("/registration/{registrationNumber}")
    public ResponseEntity<Vehicle> getVehicleByRegistrationNumber(@PathVariable String registrationNumber) {
        return vehicleService.getVehicleByRegistrationNumber(registrationNumber)
                .map(vehicle -> new ResponseEntity<>(vehicle, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Return 404 if not found
    }

    // Get vehicles by assigned driver
    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<Vehicle>> getVehiclesByDriver(@PathVariable Long driverId) {
        User driver = new User();
        driver.setId(driverId);
        List<Vehicle> vehicles = vehicleService.getVehiclesByDriver(driver);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    // Get vehicles by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Vehicle>> getVehiclesByStatus(@PathVariable Vehicle.VehicleStatus status) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByStatus(status);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    // Update vehicle details
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicleDetails) {
        try {
            Vehicle updatedVehicle = vehicleService.updateVehicle(id, vehicleDetails);
            return new ResponseEntity<>(updatedVehicle, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Not found status if vehicle does not exist
        }
    }

    // Delete a vehicle
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        try {
            vehicleService.deleteVehicle(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // No content status after deletion
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Not found status if vehicle does not exist
        }
    }
}
