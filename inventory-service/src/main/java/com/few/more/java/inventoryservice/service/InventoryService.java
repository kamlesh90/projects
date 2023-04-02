package com.few.more.java.inventoryservice.service;


import com.few.more.java.inventoryservice.model.Inventory;
import com.few.more.java.inventoryservice.repo.InventoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class InventoryService {
    @Autowired
    private InventoryRepo inventoryRepo;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode){

        return inventoryRepo.findBySkuCodeIn(skuCode).stream().map(inventory ->
                        InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity()>0)
                                .build()
                ).toList();
    }
}
