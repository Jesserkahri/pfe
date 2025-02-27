package com.example.pfe.services;

import com.example.pfe.models.Equipment;
import java.util.List;

public interface EquipmentService {
    Equipment createEquipment(Equipment equipment);

    Equipment getEquipmentById(Long id);

    List<Equipment> getAllEquipment();

    Equipment updateEquipment(Long id, Equipment equipment);

    void deleteEquipment(Long id);

    List<Equipment> getEquipmentByType(Equipment.EquipmentType type);

    List<Equipment> getEquipmentByStatus(Equipment.EquipmentStatus status);
}
