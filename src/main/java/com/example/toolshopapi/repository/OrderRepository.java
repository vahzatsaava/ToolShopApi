package com.example.toolshopapi.repository;

import com.example.toolshopapi.model.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<List<Order>> findAllByUserEmail(String userEmail);
}
