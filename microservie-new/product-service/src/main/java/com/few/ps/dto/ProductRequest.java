package com.few.ps.dto;

import lombok.*;

import java.math.BigDecimal;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;

}
