package com.example.toolshopapi.mapping;

import com.example.toolshopapi.dto.product_dto.ProductImageDto;
import com.example.toolshopapi.model.models.product.ProductImage;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {

    ProductImageDto toDto(ProductImage productImage);

    @InheritInverseConfiguration
    ProductImage toEntity(ProductImageDto productImageDto);
}
