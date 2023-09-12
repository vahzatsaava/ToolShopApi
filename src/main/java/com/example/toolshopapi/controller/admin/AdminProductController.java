package com.example.toolshopapi.controller.admin;

import com.example.toolshopapi.dto.general.ResponseDto;
import com.example.toolshopapi.dto.product_dto.ProductDto;
import com.example.toolshopapi.dto.product_dto.ProductInputDto;
import com.example.toolshopapi.service.iterfaces.ProductImageService;
import com.example.toolshopapi.service.iterfaces.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/admin")
@SecurityRequirement(name = "bearerAuth")
public class AdminProductController {
    private final ProductService productService;
    private final ProductImageService productImageService;

    @PostMapping("/create-product/{quantity}")
    @Operation(summary = "[Admin] Create a new product",
            description = "This API is used by administrators to create a new product.")
    public ResponseEntity<ResponseDto<ProductDto>> createProduct(@RequestBody @Valid ProductInputDto productDto, @PathVariable  Integer quantity){
        ProductDto productDtoSaved = productService.save(productDto,quantity);
        ResponseDto<ProductDto> responseDto = new ResponseDto<>(HttpStatus.CREATED.value(), productDtoSaved);

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/update-product")
    @Operation(summary = "[Admin] Update a new product",
            description = "This API is used by administrators to update a new product.")
    public ResponseEntity<ResponseDto<ProductDto>> updateProduct(@RequestBody @Valid ProductDto productDto){
        ProductDto productDtoSaved = productService.update(productDto);
        ResponseDto<ProductDto> responseDto = new ResponseDto<>(HttpStatus.ACCEPTED.value(), productDtoSaved);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/delete-product/{name}")
    @Operation(summary = "[Admin] Delete product",
            description = "This API is used by administrators to delete product.")
    public ResponseEntity<ResponseDto<String>> updateProduct(@PathVariable String name) {
        productService.delete(name);
        ResponseDto<String> responseDto = new ResponseDto<>(HttpStatus.OK.value(),
                String.format("user with name %s successfully deleted", name));

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "[Admin] Add  photo to product information",
            description = "This API is used by administrators to add photo to product information.")
    public ResponseEntity<ResponseDto<String>> addPictureToProduct(@RequestParam MultipartFile multipartFile,
                                                                   @RequestParam String name) {
        productImageService.saveImage(multipartFile, name);
        ResponseDto<String> responseDto = new ResponseDto<>(HttpStatus.OK.value(),
                String.format("photo to product with name %s successfully uploaded", name));

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "[Admin] Update  photo to product information",
            description = "This API is used by administrators to update photo to product information.")
    public ResponseEntity<ResponseDto<String>> updatePictureToProduct(@RequestParam MultipartFile multipartFile,
                                                                      @RequestParam Long productImageID) {
        productImageService.updateImage(multipartFile, productImageID);
        ResponseDto<String> responseDto = new ResponseDto<>(HttpStatus.OK.value(),
                String.format("photo with id %s successfully uploaded", productImageID));

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/delete-image/{productImageID}")
    @Operation(summary = "[Admin] Delete product photo ",
            description = "This API is used by administrators to delete photo from product information.")
    public ResponseEntity<ResponseDto<String>> deletePictureFromProduct(@PathVariable Long productImageID) {
        productImageService.deleteImage(productImageID);
        ResponseDto<String> responseDto = new ResponseDto<>(HttpStatus.OK.value(),
                String.format("photo  with id %s successfully deleted", productImageID));

        return ResponseEntity.ok(responseDto);
    }


}
