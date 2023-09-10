package com.example.toolshopapi.controller.admin;

import com.example.toolshopapi.dto.general.ResponseDto;
import com.example.toolshopapi.dto.product_dto.ProductDto;
import com.example.toolshopapi.service.iterfaces.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/admin")
@SecurityRequirement(name = "bearerAuth")
public class AdminProductController {
    private final ProductService productService;

    @PostMapping("/create-product/{quantity}")
    @Operation(summary = "[Admin] Create a new product",
            description = "This API is used by administrators to create a new product.")
    public ResponseEntity<ResponseDto<ProductDto>> createProduct(@RequestBody @Valid ProductDto productDto, @PathVariable  Integer quantity){
        ProductDto productDtoSaved = productService.save(productDto,quantity);
        ResponseDto<ProductDto> responseDto = new ResponseDto<>(HttpStatus.CREATED.value(), productDtoSaved);

        return ResponseEntity.ok(responseDto);
    }
    @PutMapping("/update-product")
    @Operation(summary = "[Admin] Create a new product",
            description = "This API is used by administrators to create a new product.")
    public ResponseEntity<ResponseDto<ProductDto>> updateProduct(@RequestBody @Valid ProductDto productDto){
        ProductDto productDtoSaved = productService.update(productDto);
        ResponseDto<ProductDto> responseDto = new ResponseDto<>(HttpStatus.ACCEPTED.value(), productDtoSaved);

        return ResponseEntity.ok(responseDto);
    }
    @DeleteMapping("/delete-product/{name}")
    @Operation(summary = "[Admin] Update product information",
            description = "This API is used by administrators to update product information.")
    public ResponseEntity<ResponseDto<String>> updateProduct(@PathVariable String name){
         productService.delete(name);
        ResponseDto<String> responseDto = new ResponseDto<>(HttpStatus.OK.value(),
                String.format("user with name %s successfully deleted",name));

        return ResponseEntity.ok(responseDto);
    }
}
