package com.example.toolshopapi.dto.order;

import lombok.Data;


@Data
public class OrderInputDto {
    private String productName;
    private Integer quantity;
}
