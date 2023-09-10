package com.example.toolshopapi.service.impl;

import com.example.toolshopapi.dto.product_dto.InventoryDto;
import com.example.toolshopapi.dto.product_dto.ProductDto;
import com.example.toolshopapi.mapping.ProductMapper;
import com.example.toolshopapi.model.models.product.Product;
import com.example.toolshopapi.repository.ProductRepository;
import com.example.toolshopapi.service.iterfaces.InventoryService;
import com.example.toolshopapi.service.iterfaces.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final InventoryService inventoryService;


    private final ProductMapper productMapper;


    @Override
    @Transactional
    public ProductDto save(ProductDto productDto,Integer availableQuantity) {
        if (productDto == null){
            throw new IllegalArgumentException("productDto is null please check value");
        }

        Product product = productMapper.toEntity(productDto);


        InventoryDto inventory = new InventoryDto();
        inventory.setProduct(product);
        inventory.setAvailableQuantity(availableQuantity);

        product = productRepository.save(product);
        inventoryService.save(inventory);

        return productMapper.toDto(product);
    }

    @Override
    @Transactional
    public ProductDto update(ProductDto productDto) {

        Product product = findProductByName(productDto.getName());

        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setName(product.getName());

        return productMapper.toDto(product);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto findByName(String name) {
        if (name == null){
            throw new IllegalArgumentException("name is null ");
        }
        Product product = findProductByName(name);

        return productMapper.toDto(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getAll() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public void delete(String name) {

        if (name == null){
            throw new IllegalArgumentException("name is null");
        }
        Product product = findProductByName(name);

        productRepository.delete(product);

    }

    private Product findProductByName(String name){
        return productRepository.findByName(name)
                        .orElseThrow(() -> new EntityNotFoundException("product with name " + name +
                                " not found"));
    }
}
