package com.example.toolshopapi.mapping;

import com.example.toolshopapi.dto.product_dto.ProductDto;
import com.example.toolshopapi.model.models.product.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDto(Product product);

    @InheritInverseConfiguration
    Product toEntity(ProductDto productDto);
}
