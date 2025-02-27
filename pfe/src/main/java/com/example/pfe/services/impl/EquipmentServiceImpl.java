package com.example.pfe.services.impl;

import com.example.pfe.models.Equipment;
import com.example.pfe.repositories.EquipmentRepository;
import com.example.pfe.services.EquipmentService;
import com.example.pfe.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;

    @Override
    @Transactional
    public Equipment createEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    @Override
    public Equipment getEquipmentById(Long id) {
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment not found with id: " + id));
    }

    @Override
    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    @Override
    @Transactional
    public Equipment updateEquipment(Long id, Equipment equipmentDetails) {
        Equipment equipment = getEquipmentById(id);

        if (equipmentDetails.getName() != null) {
            equipment.setName(equipmentDetails.getName());
        }

        if (equipmentDetails.getType() != null) {
            equipment.setType(equipmentDetails.getType());
        }

        if (equipmentDetails.getStatus() != null) {
            equipment.setStatus(equipmentDetails.getStatus());
        }

        return equipmentRepository.save(equipment);
    }

    @Override
    @Transactional
    public void deleteEquipment(Long id) {
        Equipment equipment = getEquipmentById(id);
        equipmentRepository.delete(equipment);
    }

    @Override
    public List<Equipment> getEquipmentByType(Equipment.EquipmentType type) {
        return equipmentRepository.findByType(type);
    }

    @Override
    public List<Equipment> getEquipmentByStatus(Equipment.EquipmentStatus status) {
        return equipmentRepository.findByStatus(status);
    }
}
