package com.example.pfe.repositories;

import com.example.pfe.models.FuelStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface FuelStockRepository extends JpaRepository<FuelStock, Long> {

    // Find stock by location (depot or internal station)
    List<FuelStock> findByLocation(String location);

    // Get total fuel quantity available in all depots
    @Query("SELECT COALESCE(SUM(fs.currentQuantity), 0) FROM FuelStock fs")
    Double getTotalFuelStock();

    // Find stocks below a specific threshold (low fuel alerts)
    @Query("SELECT fs FROM FuelStock fs WHERE fs.currentQuantity < :threshold")
    List<FuelStock> findLowStock(double threshold);

    Optional<FuelStock> findById(Long id);
}
