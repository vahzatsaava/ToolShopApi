package com.example.toolshopapi.dto;

import com.example.toolshopapi.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;

    private UserDto user;

    private List<OrderItemDto> orderItems;

    private LocalDateTime orderDate;

    private OrderStatus status;

}
