package com.example.toolshopapi.dto.product_dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDto {

    private Long id;

    private Integer availableQuantity;

    private LocalDateTime receiptTime;
}
