package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.OrderDTO;
import com.dmtryii.wms.model.Order;
import com.dmtryii.wms.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrder() {
        List<Order> orders = orderService.getAllOrder();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("{order_id}")
    public ResponseEntity<Order> getOrderById(@PathVariable(name = "order_id") Long orderId) {
        Order order = orderService.getOrderById(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("user/{user_id}")
    public ResponseEntity<List<Order>> getAllOrderByUserId(@PathVariable(name = "user_id") Long userId) {
        List<Order> orders = orderService.getAllOrderByUserId(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("user/{user_id}")
    public ResponseEntity<Order> createOrder(@PathVariable(name = "user_id") Long userId,
                                             @RequestBody OrderDTO orderDTO) {
        Order order = orderService.createOrder(userId, orderDTO);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PostMapping("{order_id}")
    public ResponseEntity<Order> updateOrder(@PathVariable(name = "order_id") Long orderId,
                                             @RequestBody OrderDTO orderDTO) {
        Order order = orderService.updateOrder(orderId, orderDTO);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("{order_id}")
    public ResponseEntity<HttpStatus> deleteOrderById(@PathVariable(name = "order_id") Long orderId) {
        orderService.deleteOrderById(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
