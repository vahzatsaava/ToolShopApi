package com.example.toolshopapi.service.iterfaces;

import com.example.toolshopapi.dto.order.OrderDto;
import com.example.toolshopapi.dto.order.OrderInputDto;
import java.security.Principal;
import java.util.List;

public interface OrderService {

    OrderDto createOrder(Principal principal, List<OrderInputDto> orderInputDto );

    List<OrderDto> findAllUserOrdersByEmail(Principal principal);

}
