package com.programmingtechie.Inventoryservice.controller;

import com.programmingtechie.Inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;
    @GetMapping("/{sku-code}")
    public boolean isAvailable(@PathVariable("sku-code") String skuCode){

        return inventoryService.isAvailable(skuCode);
    }
}
