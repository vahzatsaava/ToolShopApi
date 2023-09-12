package com.example.toolshopapi.service.impl;

import com.example.toolshopapi.dto.product_dto.ProductDto;
import com.example.toolshopapi.dto.product_dto.ProductInputDto;
import com.example.toolshopapi.dto.product_dto.ProductInputSortDto;
import com.example.toolshopapi.mapping.ProductMapper;
import com.example.toolshopapi.model.models.product.Inventory;
import com.example.toolshopapi.model.models.product.Product;
import com.example.toolshopapi.repository.ProductRepository;
import com.example.toolshopapi.service.iterfaces.InventoryService;
import com.example.toolshopapi.service.iterfaces.ProductService;
import com.example.toolshopapi.utils.ProductSpecifications;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final InventoryService inventoryService;


    private final ProductMapper productMapper;



    @Override
    @Transactional
    public ProductDto save(ProductInputDto productDto, Integer availableQuantity) {
        if (productDto == null){
            throw new IllegalArgumentException("productDto is null please check value");
        }

        Product product = new Product();

        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setCategory(productDto.getCategory());

        product = productRepository.save(product);


        Inventory inventory = new Inventory();
        inventory.setAvailableQuantity(availableQuantity);

        inventory.setProduct(product);

        inventoryService.save(inventory);

        return productMapper.toDto(product);
    }

    @Override
    @Transactional
    public ProductDto update(ProductDto productDto) {
        if (productDto == null){
            throw new IllegalArgumentException("productDto is null please check value");
        }

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

        if (name == null) {
            throw new IllegalArgumentException("name is null");
        }
        Product product = findProductByName(name);

        productRepository.delete(product);

    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDto> searchAndSortProducts(ProductInputSortDto productInputSortDto) {
        Specification<Product> specification = buildProductSpecification(productInputSortDto);

        Sort sort = Sort.by(productInputSortDto.getSortDirection().equals("asc") ? Sort.Order.asc("price") : Sort.Order.desc("price"));
        Pageable pageable = PageRequest.of(productInputSortDto.getPage(), productInputSortDto.getSize(), sort);

        Page<Product> productPage = productRepository.findAll(specification, pageable);

        return productPage.map(productMapper::toDto);
    }

    private Product findProductByName(String name) {
        return productRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("product with name " + name +
                        " not found"));
    }

    private Specification<Product> buildProductSpecification(ProductInputSortDto productInputSortDto) {
        Specification<Product> specification = Specification.where(null);

        if (productInputSortDto.getMinPrice() != null && productInputSortDto.getMaxPrice() != null) {
            specification = specification.and(ProductSpecifications.hasPriceBetween(productInputSortDto.getMinPrice(), productInputSortDto.getMaxPrice()));
            return specification;
        }

        if (productInputSortDto.getMinPrice() != null) {
            specification = specification.and(ProductSpecifications.hasPriceGreaterThanEqual(productInputSortDto.getMinPrice()));
            return specification;
        }

        if (productInputSortDto.getMaxPrice() != null) {
            specification = specification.and(ProductSpecifications.hasPriceLessThanEqual(productInputSortDto.getMaxPrice()));
            return specification;
        }

        if (productInputSortDto.getCategory() != null) {
            specification = specification.and(ProductSpecifications.hasCategory(productInputSortDto.getCategory()));
            return specification;
        }

        return specification;
    }

}
