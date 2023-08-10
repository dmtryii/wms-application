package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.response.OrderDTO;
import com.dmtryii.wms.dto.response.OrderLineDTO;
import com.dmtryii.wms.dto.request.OrderLineRequest;
import com.dmtryii.wms.dto.request.OrderRequest;
import com.dmtryii.wms.exception.ResourceNotCreatedException;
import com.dmtryii.wms.exception.handle_exception.BadRequestRecorder;
import com.dmtryii.wms.model.Order;
import com.dmtryii.wms.model.OrderLine;
import com.dmtryii.wms.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final BadRequestRecorder errorRecorder;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrder() {
        List<OrderDTO> orders = orderService.getAllOrder()
                .stream()
                .map(this::map)
                .toList();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<OrderDTO> createOrder(@RequestBody OrderRequest orderRequest,
                                                 Principal principal,
                                                 BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errors = errorRecorder.getRecordedErrors(bindingResult);
            throw new ResourceNotCreatedException(errors);
        }
        Order order = orderService.createOrder(orderRequest, principal);
        return new ResponseEntity<>(
                map(order),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/products")
    public ResponseEntity<OrderLineDTO> addProductToOrder(@RequestBody @Valid OrderLineRequest orderLineRequest,
                                                          BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errors = errorRecorder.getRecordedErrors(bindingResult);
            throw new ResourceNotCreatedException(errors);
        }
        OrderLine orderLine = orderService.addProductToOrder(orderLineRequest);
        return new ResponseEntity<>(
                map(orderLine),
                HttpStatus.OK
        );
    }

    @GetMapping("/{order_id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable(name = "order_id") Long orderId) {
        Order order = orderService.getOrderById(orderId);
        return new ResponseEntity<>(
                map(order),
                HttpStatus.OK
        );
    }

    @GetMapping("/users/{user_id}")
    public ResponseEntity<List<OrderDTO>> getAllOrderByUserId(@PathVariable(name = "user_id") Long userId) {
        List<OrderDTO> orders = orderService.getAllOrderByUserId(userId)
                .stream()
                .map(this::map)
                .toList();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/{order_id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable(name = "order_id") Long orderId,
                                                @RequestBody OrderRequest orderRequest) {
        Order order = orderService.updateOrder(orderId, orderRequest);
        return new ResponseEntity<>(
                map(order),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{order_id}")
    public ResponseEntity<HttpStatus> deleteOrderById(@PathVariable(name = "order_id") Long orderId) {
        orderService.deleteOrderById(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private OrderDTO map(Order order) {
        return modelMapper.map(order, OrderDTO.class);
    }
    private OrderLineDTO map(OrderLine orderLine) {
        return modelMapper.map(orderLine, OrderLineDTO.class);
    }
}
