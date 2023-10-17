package com.example.toolshopapi.service.utils;

import com.example.toolshopapi.model.models.product.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecifications {
    private ProductSpecifications(){

    }
    private static final String PRICE ="price";

    public static Specification<Product> hasCategory(String category) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"), category);
    }

    public static Specification<Product> hasPriceGreaterThanEqual(BigDecimal price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(PRICE), price);
    }

    public static Specification<Product> hasPriceLessThanEqual(BigDecimal price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get(PRICE), price);
    }

    public static Specification<Product> hasPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PRICE), minPrice));
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PRICE), maxPrice));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
