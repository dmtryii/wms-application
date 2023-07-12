package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.OrderDTO;
import com.dmtryii.wms.dto.OrderLineDTO;
import com.dmtryii.wms.dto.UserDTO;
import com.dmtryii.wms.dto.request.OrderLineRequest;
import com.dmtryii.wms.dto.request.OrderRequest;
import com.dmtryii.wms.service.OrderService;
import com.dmtryii.wms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUser() {
        List<UserDTO> users = userService.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderRequest orderRequest,
                                                Principal principal) {
        OrderDTO order = orderService.createOrder(orderRequest, principal);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PutMapping("/orders/products")
    public ResponseEntity<OrderLineDTO> addProductToOrder(@RequestBody OrderLineRequest orderLineRequest) {
        OrderLineDTO orderLine = orderService.addProductToOrder(orderLineRequest);
        return new ResponseEntity<>(orderLine, HttpStatus.OK);
    }
}
