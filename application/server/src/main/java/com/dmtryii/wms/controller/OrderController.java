package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.OrderDTO;
import com.dmtryii.wms.dto.request.OrderRequest;
import com.dmtryii.wms.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrder() {
        List<OrderDTO> orders = orderService.getAllOrder();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("{order_id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable(name = "order_id") Long orderId) {
        OrderDTO order = orderService.getOrderById(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("user/{user_id}")
    public ResponseEntity<List<OrderDTO>> getAllOrderByUserId(@PathVariable(name = "user_id") Long userId) {
        List<OrderDTO> orders = orderService.getAllOrderByUserId(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderRequest orderRequest) {
        OrderDTO order = orderService.createOrder(orderRequest);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PostMapping("{order_id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable(name = "order_id") Long orderId,
                                                @RequestBody OrderRequest orderRequest) {
        OrderDTO order = orderService.updateOrder(orderId, orderRequest);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("{order_id}")
    public ResponseEntity<HttpStatus> deleteOrderById(@PathVariable(name = "order_id") Long orderId) {
        orderService.deleteOrderById(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
