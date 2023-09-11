package com.example.toolshopapi.service.iterfaces;

import org.springframework.web.multipart.MultipartFile;

public interface ProductImageService {
    void saveImage(MultipartFile multipartFile,String productName);
    void updateImage(MultipartFile multipartFile,Long productImageId);
    void deleteImage(Long id);
}
