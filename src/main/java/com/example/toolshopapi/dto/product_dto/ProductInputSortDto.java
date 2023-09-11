package com.example.toolshopapi.dto.product_dto;

import lombok.*;

import java.math.BigDecimal;

@Data
public class ProductInputSortDto {

    private String category;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String sortDirection;
    private int page;
    private int size;

}
