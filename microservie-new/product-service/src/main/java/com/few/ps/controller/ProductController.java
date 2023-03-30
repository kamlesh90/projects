package com.few.ps.controller;

import com.few.ps.dto.ProductRequest;
import com.few.ps.dto.ProductResponse;
import com.few.ps.model.Product;
import com.few.ps.service.ProductService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
        log.info("product saved success!");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProduct(){
        return  productService.getAllProduct();
    }

}
