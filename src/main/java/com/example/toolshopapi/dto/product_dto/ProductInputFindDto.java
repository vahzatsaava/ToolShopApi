package com.example.toolshopapi.dto.product_dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ProductInputFindDto {
    @NotBlank(message = "Query must not be blank")
    private String query;

    @PositiveOrZero(message = "Page must be non-negative")
    private int page;

    @PositiveOrZero(message = "Size must be non-negative")
    private int size;
}
