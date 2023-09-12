package com.example.toolshopapi.controller.order;

import com.example.toolshopapi.dto.general.ResponseDto;
import com.example.toolshopapi.dto.order.OrderDto;
import com.example.toolshopapi.dto.order.OrderInputDto;
import com.example.toolshopapi.service.iterfaces.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
@SecurityRequirement(name = "bearerAuth")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create-order")
    @Operation(summary = "Update user address and name",
            description = "This API is used to update the address and name of the authenticated user.")
    public ResponseEntity<ResponseDto<OrderDto>> updateUserAddressAndName(Principal principal,
                                                                          @RequestBody List<OrderInputDto> orderItemDto
    ){
        OrderDto updated = orderService.createOrder(principal,orderItemDto);
        ResponseDto<OrderDto> responseDto = new ResponseDto<>(HttpStatus.OK.value(), updated);

        return ResponseEntity.ok(responseDto);
    }
    @GetMapping("/all-orders-by-user")
    @Operation(summary = "Update user address and name",
            description = "This API is used to update the address and name of the authenticated user.")
    public ResponseEntity<ResponseDto<List<OrderDto>>> getAllUsersOrders(Principal principal){
        List<OrderDto> updated = orderService.findAllUserOrdersByEmail(principal);
        ResponseDto<List<OrderDto>> responseDto = new ResponseDto<>(HttpStatus.OK.value(), updated);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/get-last-order")
    @Operation(summary = "Update user address and name",
            description = "This API is used to update the address and name of the authenticated user.")
    public ResponseEntity<ResponseDto<OrderDto>> getLastOrder(Principal principal){
        OrderDto updated = orderService.findLastUserOrder(principal);
        ResponseDto<OrderDto> responseDto = new ResponseDto<>(HttpStatus.OK.value(), updated);

        return ResponseEntity.ok(responseDto);
    }
}
