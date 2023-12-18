package com.example.toolshopapi.dto.product_dto;

import com.example.toolshopapi.dto.CommentModel;
import com.example.toolshopapi.model.enums.LabelStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;

    @NotBlank(message = "Product name cannot be empty")
    @NotNull(message = "Product name cannot be null")
    private String name;

    @NotBlank(message = "Product description cannot be empty")
    private String description;

    @NotBlank(message = "Product category cannot be empty")
    private String category;

    private Integer soldQuantity;

    @NotNull(message = "Product price cannot be null")
    @Positive(message = "Product price must be a positive number")
    private BigDecimal price;

    private LabelStatus label;

    private InventoryDto inventory;

    private List<Long> relatedProducts;

    private List<ProductImageDto> images;

    private List<CommentModel> comments;



}
