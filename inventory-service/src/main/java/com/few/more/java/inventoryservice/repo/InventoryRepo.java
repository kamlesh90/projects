package com.few.more.java.inventoryservice.repo;

import com.few.more.java.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepo extends JpaRepository<Inventory, Long> {
    //Optional<Inventory> findBySkuCode(String skuCode);
    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
