package com.example.toolshopapi.service.impl;

import com.example.toolshopapi.dto.product_dto.ProductDto;
import com.example.toolshopapi.exceptions.InternalServerErrorException;
import com.example.toolshopapi.mapping.ProductMapper;
import com.example.toolshopapi.model.models.product.ProductImage;
import com.example.toolshopapi.repository.ProductImageRepository;
import com.example.toolshopapi.service.iterfaces.ProductImageService;
import com.example.toolshopapi.service.iterfaces.ProductService;
import com.example.toolshopapi.utils.PhotoValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
@Slf4j
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageRepository productImageRepository;
    private final ProductService productService;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public void saveImage(MultipartFile multipartFile, String productName) {
        if (productName == null){
            throw new IllegalArgumentException("productName is null");
        }
        PhotoValidator.checkPhoto(multipartFile);

        ProductImage productImage = createProductImageFromMultipartFile(multipartFile);

        ProductDto productDto = productService.findByName(productName);

        productImage.setProduct(productMapper.toEntity(productDto));
        productImageRepository.save(productImage);
    }

    @Override
    @Transactional
    public void updateImage(MultipartFile multipartFile, Long productImageId) {
        if (productImageId == null){
            throw new IllegalArgumentException("productName is null");
        }
        PhotoValidator.checkPhoto(multipartFile);
        final String fileName = multipartFile.getOriginalFilename();

        ProductImage productImage = findByID(productImageId);

        try {
            productImage.setImage(multipartFile.getBytes());
        } catch (IOException e) {
            log.error("Error occurred when uploading image with name {}", fileName);
            throw InternalServerErrorException.failedToSaveImage();
        }
        productImageRepository.save(productImage);

    }

    @Override
    @Transactional
    public void deleteImage(Long productImageId) {
        if (productImageId == null){
            throw new IllegalArgumentException("productName is null");
        }
        ProductImage productImage = findByID(productImageId);

        productImageRepository.delete(productImage);
    }


    private ProductImage findByID(Long productImageId){
        return productImageRepository
                .findById(productImageId)
                .orElseThrow(() -> new EntityNotFoundException("image with id " + productImageId + " not found"));
    }
    private ProductImage createProductImageFromMultipartFile(MultipartFile multipartFile) {
        final String fileName = multipartFile.getOriginalFilename();
        ProductImage productImage = new ProductImage();
        try {
            productImage.setImage(multipartFile.getBytes());
        } catch (IOException e) {
            log.error("Error occurred when uploading image with name {}", fileName);
            throw InternalServerErrorException.failedToSaveImage();
        }
        return productImage;
    }
}
