package com.example.toolshopapi.dto.order;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderInputDto {
    private String productName;
    private Integer quantity;
    private BigDecimal subtotal;
}
