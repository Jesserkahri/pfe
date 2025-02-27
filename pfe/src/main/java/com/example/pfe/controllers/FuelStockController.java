package com.example.pfe.controllers;

import com.example.pfe.exceptions.ResourceNotFoundException;
import com.example.pfe.models.FuelStock;
import com.example.pfe.services.FuelStockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fuel-stocks")
public class FuelStockController {

    private final FuelStockService fuelStockService;

    // Constructor injection of FuelStockService
    public FuelStockController(FuelStockService fuelStockService) {
        this.fuelStockService = fuelStockService;
    }

    // Get all fuel stocks
    @GetMapping
    public ResponseEntity<List<FuelStock>> getAllFuelStocks() {
        List<FuelStock> fuelStocks = fuelStockService.getAllFuelStocks();
        return new ResponseEntity<>(fuelStocks, HttpStatus.OK);
    }

    // Get a specific fuel stock by its ID
    @GetMapping("/{id}")
    public ResponseEntity<FuelStock> getFuelStockById(@PathVariable Long id) {
        try {
            FuelStock fuelStock = fuelStockService.getFuelStockById(id);
            return new ResponseEntity<>(fuelStock, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a new fuel stock
    @PostMapping
    public ResponseEntity<FuelStock> createFuelStock(@RequestBody FuelStock fuelStock) {
        FuelStock createdFuelStock = fuelStockService.createFuelStock(fuelStock);
        return new ResponseEntity<>(createdFuelStock, HttpStatus.CREATED);
    }

    // Update an existing fuel stock by its ID
    @PutMapping("/{id}")
    public ResponseEntity<FuelStock> updateFuelStock(@PathVariable Long id, @RequestBody FuelStock updatedFuelStock) {
        try {
            FuelStock updated = fuelStockService.updateFuelStock(id, updatedFuelStock);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a fuel stock by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuelStock(@PathVariable Long id) {
        try {
            fuelStockService.deleteFuelStock(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Check if fuel quantity is below the minimum threshold
    @GetMapping("/{id}/is-below-threshold")
    public ResponseEntity<Boolean> isFuelBelowThreshold(@PathVariable Long id) {
        try {
            boolean isBelowThreshold = fuelStockService.isFuelBelowThreshold(id);
            return new ResponseEntity<>(isBelowThreshold, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update fuel stock quantity (add/subtract)
    @PutMapping("/{id}/update-quantity")
    public ResponseEntity<FuelStock> updateFuelQuantity(@PathVariable Long id, @RequestParam Double quantity) {
        try {
            FuelStock updatedFuelStock = fuelStockService.updateFuelQuantity(id, quantity);
            return new ResponseEntity<>(updatedFuelStock, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
