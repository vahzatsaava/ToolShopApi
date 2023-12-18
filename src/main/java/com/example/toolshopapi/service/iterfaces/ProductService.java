package com.example.toolshopapi.service.iterfaces;

import com.example.toolshopapi.dto.product_dto.ProductDto;
import com.example.toolshopapi.dto.product_dto.ProductInputDto;
import com.example.toolshopapi.dto.product_dto.ProductInputFindDto;
import com.example.toolshopapi.dto.product_dto.ProductInputSortDto;
import com.example.toolshopapi.model.models.product.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    ProductDto save(ProductInputDto productDto, Integer availableQuantity);

    ProductDto update(ProductDto productDto);

    ProductDto findByName(String name);

    Product findById(Long productId);

    List<ProductDto> getAll();

    void delete(String name);

    List<String> getAllCategory();

    Page<ProductDto> searchAndSortProducts(ProductInputSortDto productInputSortDto);

    Page<ProductDto> findProductsByNameOrByDescription(ProductInputFindDto productInputFindDto);
}
