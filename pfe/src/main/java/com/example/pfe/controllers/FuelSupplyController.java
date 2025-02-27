package com.example.pfe.controllers;

import com.example.pfe.models.FuelSupply;
import com.example.pfe.models.FuelStock;
import com.example.pfe.models.User;
import com.example.pfe.services.FuelSupplyService;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/fuel-supplies")
public class FuelSupplyController {

    private final FuelSupplyService fuelSupplyService;

    public FuelSupplyController(FuelSupplyService fuelSupplyService) {
        this.fuelSupplyService = fuelSupplyService;
    }

    // Get all fuel supplies
    @GetMapping
    public ResponseEntity<List<FuelSupply>> getAllFuelSupplies() {
        List<FuelSupply> fuelSupplies = fuelSupplyService.getAllFuelSupplies();
        return new ResponseEntity<>(fuelSupplies, HttpStatus.OK);
    }

    // Get fuel supply by ID
    @GetMapping("/{id}")
    public ResponseEntity<FuelSupply> getFuelSupplyById(@PathVariable Long id) {
        FuelSupply fuelSupply = fuelSupplyService.getFuelSupplyById(id);
        return new ResponseEntity<>(fuelSupply, HttpStatus.OK);
    }

    // Get fuel supplies by supplier name
    @GetMapping("/supplier/{supplierName}")
    public ResponseEntity<List<FuelSupply>> getFuelSuppliesBySupplier(@PathVariable String supplierName) {
        List<FuelSupply> fuelSupplies = fuelSupplyService.getFuelSuppliesBySupplier(supplierName);
        return new ResponseEntity<>(fuelSupplies, HttpStatus.OK);
    }

    // Get fuel supplies by date range
    @GetMapping("/date-range")
    public ResponseEntity<List<FuelSupply>> getFuelSuppliesByDateRange(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        List<FuelSupply> fuelSupplies = fuelSupplyService.getFuelSuppliesByDateRange(start, end);
        return new ResponseEntity<>(fuelSupplies, HttpStatus.OK);
    }

    // Get total fuel supplied in a given date range
    @GetMapping("/total-supplied")
    public ResponseEntity<Double> getTotalSuppliedBetween(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        Double totalSupplied = fuelSupplyService.getTotalSuppliedBetween(start, end);
        return new ResponseEntity<>(totalSupplied, HttpStatus.OK);
    }

    // Get fuel supplies by fuel stock
    @GetMapping("/fuel-stock/{fuelStockId}")
    public ResponseEntity<List<FuelSupply>> getFuelSuppliesByFuelStock(@PathVariable Long fuelStockId) {
        FuelStock fuelStock = new FuelStock();
        fuelStock.setId(fuelStockId); // Assuming fuel stock exists with the provided ID
        List<FuelSupply> fuelSupplies = fuelSupplyService.getFuelSuppliesByFuelStock(fuelStock);
        return new ResponseEntity<>(fuelSupplies, HttpStatus.OK);
    }

    // Get fuel supplies by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FuelSupply>> getFuelSuppliesByUser(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId); // Assuming user exists with the provided ID
        List<FuelSupply> fuelSupplies = fuelSupplyService.getFuelSuppliesByUser(user);
        return new ResponseEntity<>(fuelSupplies, HttpStatus.OK);
    }

    // Add a new fuel supply
    @PostMapping
    public ResponseEntity<FuelSupply> addFuelSupply(@RequestBody FuelSupply fuelSupply) {
        FuelSupply savedFuelSupply = fuelSupplyService.addFuelSupply(fuelSupply);
        return new ResponseEntity<>(savedFuelSupply, HttpStatus.CREATED);
    }
}
