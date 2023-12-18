package com.example.toolshopapi.mapping.order;

import com.example.toolshopapi.dto.order.OrderDto;
import com.example.toolshopapi.mapping.ProductMapper;
import com.example.toolshopapi.model.models.Order;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface OrderMapper {

    OrderDto toDto(Order order);

    @InheritInverseConfiguration
    Order toEntity(OrderDto orderDto);
}
