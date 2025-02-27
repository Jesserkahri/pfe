package com.example.pfe.repositories;

import com.example.pfe.models.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findByType(Equipment.EquipmentType type);

    List<Equipment> findByStatus(Equipment.EquipmentStatus status);
}
