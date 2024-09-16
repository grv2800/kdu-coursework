package com.assessment.assessment.repository;

import com.assessment.assessment.entity.Inventory;
import com.assessment.assessment.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory,Long> {
    Optional<Inventory> findByProduct(Product product);
}
