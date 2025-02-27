package com.example.pfe.controllers;

import com.example.pfe.exceptions.ResourceNotFoundException;
import com.example.pfe.models.Equipment;
import com.example.pfe.services.EquipmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    // Constructor injection of EquipmentService
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    // Create new equipment
    @PostMapping
    public ResponseEntity<Equipment> createEquipment(@RequestBody Equipment equipment) {
        Equipment createdEquipment = equipmentService.createEquipment(equipment);
        return new ResponseEntity<>(createdEquipment, HttpStatus.CREATED);
    }

    // Get a specific equipment by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable Long id) {
        try {
            Equipment equipment = equipmentService.getEquipmentById(id);
            return new ResponseEntity<>(equipment, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all equipment
    @GetMapping
    public ResponseEntity<List<Equipment>> getAllEquipment() {
        List<Equipment> equipmentList = equipmentService.getAllEquipment();
        return new ResponseEntity<>(equipmentList, HttpStatus.OK);
    }

    // Update an existing equipment by its ID
    @PutMapping("/{id}")
    public ResponseEntity<Equipment> updateEquipment(@PathVariable Long id, @RequestBody Equipment equipmentDetails) {
        try {
            Equipment updatedEquipment = equipmentService.updateEquipment(id, equipmentDetails);
            return new ResponseEntity<>(updatedEquipment, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete equipment by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Long id) {
        try {
            equipmentService.deleteEquipment(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get equipment by type
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Equipment>> getEquipmentByType(@PathVariable Equipment.EquipmentType type) {
        List<Equipment> equipmentList = equipmentService.getEquipmentByType(type);
        return new ResponseEntity<>(equipmentList, HttpStatus.OK);
    }

    // Get equipment by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Equipment>> getEquipmentByStatus(@PathVariable Equipment.EquipmentStatus status) {
        List<Equipment> equipmentList = equipmentService.getEquipmentByStatus(status);
        return new ResponseEntity<>(equipmentList, HttpStatus.OK);
    }
}
