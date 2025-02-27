package com.example.pfe.repositories;

import com.example.pfe.models.FuelStock;
import com.example.pfe.models.FuelSupply;
import com.example.pfe.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface FuelSupplyRepository extends JpaRepository<FuelSupply, Long> {

    // Find supplies by supplier name
    List<FuelSupply> findBySupplierName(String supplierName);

    // Find supplies between specific dates
    @Query("SELECT fs FROM FuelSupply fs WHERE fs.deliveryDate BETWEEN :startDate AND :endDate")
    List<FuelSupply> findByDateRange(LocalDateTime startDate, LocalDateTime endDate);

    // Get total quantity supplied in a given period
    @Query("SELECT COALESCE(SUM(fs.quantity), 0) FROM FuelSupply fs WHERE fs.deliveryDate BETWEEN :startDate AND :endDate")
    Double getTotalSuppliedBetween(LocalDateTime startDate, LocalDateTime endDate);

    Optional<FuelSupply> findById(Long id);

    List<FuelSupply> findByFuelStock(FuelStock fuelStock);

    List<FuelSupply> findByReceivedBy(User user);
}
