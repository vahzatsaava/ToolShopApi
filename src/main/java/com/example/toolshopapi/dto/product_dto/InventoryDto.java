package com.example.toolshopapi.dto.product_dto;

import com.example.toolshopapi.model.models.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDto {

    private Long id;

    private Product product;

    private Integer availableQuantity;
}
