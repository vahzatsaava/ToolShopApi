package com.example.toolshopapi.service.impl;

import com.example.toolshopapi.dto.UserDto;
import com.example.toolshopapi.dto.order.OrderDto;
import com.example.toolshopapi.dto.order.OrderInputDto;
import com.example.toolshopapi.dto.product_dto.ProductDto;
import com.example.toolshopapi.mapping.ProductMapper;
import com.example.toolshopapi.mapping.UserMapper;
import com.example.toolshopapi.mapping.order.OrderMapper;
import com.example.toolshopapi.model.enums.OrderStatus;
import com.example.toolshopapi.model.models.Order;
import com.example.toolshopapi.model.models.OrderItem;
import com.example.toolshopapi.repository.OrderRepository;
import com.example.toolshopapi.service.iterfaces.OrderItemService;
import com.example.toolshopapi.service.iterfaces.OrderService;
import com.example.toolshopapi.service.iterfaces.ProductService;
import com.example.toolshopapi.service.iterfaces.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final UserService userService;
    private final ProductService productService;
    private final OrderItemService orderItemService;
    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;
    private final UserMapper userMapper;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public OrderDto createOrder(Principal principal, List<OrderInputDto> orderInputDtos) {
        if (orderInputDtos == null) {
            throw new IllegalArgumentException("orderItemDto is null ");
        }
        UserDto userDto = userService.findUserByEmail(principal.getName());

        List<OrderItem> orderItems = new ArrayList<>();

        Order order = saveOrder(userDto);

        saveOrderItem(orderInputDtos, order, orderItems);

        return orderMapper.toDto(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> findAllUserOrdersByEmail(Principal principal) {
        List<Order> orders = orderRepository.findAllByUserEmail(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("Orders is not exists by " + principal.getName()));
        return orders.stream().map(orderMapper::toDto)
                .toList();
    }

    private Order saveOrder(UserDto userDto) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setUser(userMapper.toEntity(userDto));
        order = orderRepository.save(order);

        return order;
    }

    private void saveOrderItem(List<OrderInputDto> orderInputDtos, Order order, List<OrderItem> orderItems) {

        for (OrderInputDto input : orderInputDtos) {
            ProductDto productDto = productService.findByName(input.getProductName());

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(productMapper.toEntity(productDto));
            orderItem.setQuantity(input.getQuantity());
            orderItem.setSubtotal(input.getSubtotal());
            orderItems.add(orderItem);

            orderItemService.save(orderItem);
        }
    }

}
