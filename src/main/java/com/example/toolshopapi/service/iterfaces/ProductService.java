package com.example.toolshopapi.service.iterfaces;

import com.example.toolshopapi.dto.product_dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto save(ProductDto productDto,Integer availableQuantity);

    ProductDto update(ProductDto productDto);

    ProductDto findByName(String name);

    List<ProductDto> getAll();

    void delete(String name);
}
