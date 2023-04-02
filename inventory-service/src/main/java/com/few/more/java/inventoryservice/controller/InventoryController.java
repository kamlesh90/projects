package com.few.more.java.inventoryservice.controller;

import com.few.more.java.inventoryservice.dto.InventoryResponse;
import com.few.more.java.inventoryservice.model.Inventory;
import com.few.more.java.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
//
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    // http://localhost:8082/api/inventory/iphone-13,iphone-13-red,mobile
    // http://localhost:8082/api/inventory?skuCode=iphone-13&skuCode=iphone-13-red&skuCode=TV
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){

       return inventoryService.isInStock(skuCode);
    }
}
