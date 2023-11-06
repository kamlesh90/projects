package com.programmingtechie.Inventoryservice.service;

import com.programmingtechie.Inventoryservice.repository.InventoryRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepositroy inventoryRepositroy;
    @Transactional(readOnly = true)
    public boolean isAvailable(String skuCode){

        return inventoryRepositroy.findBySkuCode(skuCode).isPresent();
    }
}
