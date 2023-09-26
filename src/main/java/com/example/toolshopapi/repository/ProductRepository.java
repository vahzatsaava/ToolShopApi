package com.example.toolshopapi.repository;

import com.example.toolshopapi.model.models.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> , JpaSpecificationExecutor<Product> {

    Optional<Product> findByName(String name);

    @Query("SELECT distinct p.category from Product p")
    Optional<List<String>> getAllCategory();

}
