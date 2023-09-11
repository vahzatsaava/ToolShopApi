package com.example.toolshopapi.controller.product;

import com.example.toolshopapi.dto.general.ResponseDto;
import com.example.toolshopapi.dto.product_dto.ProductDto;
import com.example.toolshopapi.dto.product_dto.ProductInputSortDto;
import com.example.toolshopapi.service.iterfaces.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/product-user")
@SecurityRequirement(name = "bearerAuth")
public class ProductUserController {
    private final ProductService productService;

    @GetMapping("/get-by-name")
    @Operation(summary = "Get product by name",
            description = "This API is used to retrieve product information by its name.")
    public ResponseEntity<ResponseDto<ProductDto>> getProductByName(@RequestParam String name){
        ProductDto productDtoSaved = productService.findByName(name);
        ResponseDto<ProductDto> responseDto = new ResponseDto<>(HttpStatus.OK.value(), productDtoSaved);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/get-products")
    @Operation(summary = "Get all products",
            description = "This API is used to retrieve a list of all available products.")
    public ResponseEntity<ResponseDto<List<ProductDto>>> getAllProducts(){
        List<ProductDto> productDtoSaved = productService.getAll();
        ResponseDto<List<ProductDto>> responseDto = new ResponseDto<>(HttpStatus.OK.value(), productDtoSaved);

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/order-products")
    @Operation(summary = "Get products with sorting",
            description = "This API is used to retrieve products with optional sorting by minimum and maximum price, and category.")
    public ResponseEntity<ResponseDto<Page<ProductDto>>> getAllOrderProducts(@RequestBody ProductInputSortDto productInputSortDto){
        Page<ProductDto> productDtoSaved = productService.searchAndSortProducts(productInputSortDto);
        ResponseDto<Page<ProductDto>> responseDto = new ResponseDto<>(HttpStatus.OK.value(), productDtoSaved);

        return ResponseEntity.ok(responseDto);
    }
}
