package com.example.toolshopapi.service.impl;

import com.example.toolshopapi.dto.UserDto;
import com.example.toolshopapi.dto.order.OrderDto;
import com.example.toolshopapi.dto.order.OrderInputDto;
import com.example.toolshopapi.dto.product_dto.ProductDto;
import com.example.toolshopapi.exceptions.QuantityProductNotValidException;
import com.example.toolshopapi.mapping.ProductMapper;
import com.example.toolshopapi.mapping.UserMapper;
import com.example.toolshopapi.mapping.order.OrderMapper;
import com.example.toolshopapi.model.enums.OrderStatus;
import com.example.toolshopapi.model.models.Order;
import com.example.toolshopapi.model.models.OrderItem;
import com.example.toolshopapi.repository.OrderRepository;
import com.example.toolshopapi.service.iterfaces.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final UserService userService;
    private final ProductService productService;
    private final OrderItemService orderItemService;
    private final InventoryService inventoryService;
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

        List<OrderItem> orderItemsSaved = saveOrderItem(orderInputDtos, order, orderItems);

        order.setOrderItems(orderItemsSaved);

        return orderMapper.toDto(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> findAllUserOrdersByEmail(Principal principal) {
        List<Order> orders = orderRepository.findAllByUserEmail(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("Orders is not exists by " + principal.getName()));
        return orders.stream().map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDto findLastUserOrder(Principal principal) {
        Order order = orderRepository.findTopByUserEmailOrderByOrderDateDesc(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("Order is not exist by  " + principal.getName()));

        return orderMapper.toDto(order);
    }

    private Order saveOrder(UserDto userDto) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setUser(userMapper.toEntity(userDto));
        order = orderRepository.save(order);

        return order;
    }

    private List<OrderItem> saveOrderItem(List<OrderInputDto> orderInputDtos, Order order, List<OrderItem> orderItems) {

        for (OrderInputDto input : orderInputDtos) {
            ProductDto productDto = productService.findByName(input.getProductName());
            int currentQuantity = productDto.getInventory().getAvailableQuantity();
            if (isValidQuantity(input, currentQuantity)) {
                OrderItem orderItem = createOrderItem(order, productDto, input);
                orderItems.add(orderItem);
                orderItemService.save(orderItem);

                inventoryService.updateInventoryQuantity(productDto.getInventory().getId(), currentQuantity - input.getQuantity());
            } else {
                throw QuantityProductNotValidException.inputIsBiggerQuantity();
            }

        }
        return orderItems;
    }

    private OrderItem createOrderItem(Order order, ProductDto productDto, OrderInputDto input) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(productMapper.toEntity(productDto));
        orderItem.setQuantity(input.getQuantity());
        orderItem.setSubtotal(productDto.getPrice().multiply(new BigDecimal(input.getQuantity())));
        return orderItem;
    }

    private boolean isValidQuantity(OrderInputDto input, int availableQuantity) {
        return availableQuantity > 0 && availableQuantity >= input.getQuantity();
    }

}
