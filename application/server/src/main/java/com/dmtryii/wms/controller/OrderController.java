package com.dmtryii.wms.controller;

import com.dmtryii.wms.model.Order;
import com.dmtryii.wms.model.User;
import com.dmtryii.wms.repository.OrderRepository;
import com.dmtryii.wms.repository.UserRepository;
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
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrder() {

        List<Order> orders = orderRepository.findAll();

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("user/{user_id}")
    public ResponseEntity<Order> createOrder(@PathVariable(name = "user_id") Long userId,
                                             @RequestBody Order order) {

        User user = userRepository.findById(userId).orElseThrow();

        Order _order = new Order(
                LocalDate.now(),
                order.getDetails(),
                user
        );

        return new ResponseEntity<>(
                orderRepository.save(_order),
                HttpStatus.CREATED
        );
    }

}
