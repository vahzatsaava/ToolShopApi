package com.example.toolshopapi.mapping;

import com.example.toolshopapi.dto.product_dto.ProductDto;
import com.example.toolshopapi.model.models.product.Product;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring", uses = {CommentsMapper.class})
public interface ProductMapper {


    @Mapping(target = "relatedProducts", source = "relatedProducts", qualifiedByName = "mapProductsToLongs")
    ProductDto toProductDto(Product product);

    @InheritInverseConfiguration
    @Mapping(target = "relatedProducts", source = "relatedProducts", qualifiedByName = "mapLongsToProducts")
    Product toProductEntity(ProductDto productDto);

    @Named("mapProductsToLongs")
    default List<Long> mapProductsToLongs(List<Product> products) {
        return products.stream()
                .map(Product::getId)
                .collect(Collectors.toList());
    }

    @Named("mapLongsToProducts")
    default List<Product> mapLongsToProducts(List<Long> ids) {
        return ids.stream()
                .map(id -> {
                    Product product = new Product();
                    product.setId(id);
                    return product;
                })
                .collect(Collectors.toList());
    }

}
