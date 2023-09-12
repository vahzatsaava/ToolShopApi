package com.example.toolshopapi.service.impl;

import com.example.toolshopapi.model.models.OrderItem;
import com.example.toolshopapi.repository.OrderItemRepository;
import com.example.toolshopapi.service.iterfaces.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    @Override
    @Transactional
    public OrderItem save(OrderItem orderItem) {
        if (orderItem == null){
            throw  new IllegalArgumentException("orderItemDto is null");
        }
        return orderItemRepository.save(orderItem);
    }
}
