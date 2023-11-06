package com.programmingtechie.Inventoryservice.repository;

import com.programmingtechie.Inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InventoryRepositroy extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findBySkuCode(String skuCode);
//    @Query("")
//    Optional<Inventory> findBySkuCode(String skuCode);

}
