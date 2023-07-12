package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.OrderDTO;
import com.dmtryii.wms.dto.OrderLineDTO;
import com.dmtryii.wms.dto.request.OrderLineRequest;
import com.dmtryii.wms.dto.request.OrderRequest;
import com.dmtryii.wms.dto_mapper.OrderDTOMapper;
import com.dmtryii.wms.model.Order;
import com.dmtryii.wms.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderDTOMapper orderDTOMapper;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrder() {
        List<OrderDTO> orders = orderService.getAllOrder();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{order_id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable(name = "order_id") Long orderId) {
        Order order = orderService.getOrderById(orderId);
        return new ResponseEntity<>(orderDTOMapper.apply(order), HttpStatus.OK);
    }

    @GetMapping("/users/{user_id}")
    public ResponseEntity<List<OrderDTO>> getAllOrderByUserId(@PathVariable(name = "user_id") Long userId) {
        List<OrderDTO> orders = orderService.getAllOrderByUserId(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/{order_id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable(name = "order_id") Long orderId,
                                                @RequestBody OrderRequest orderRequest) {
        OrderDTO order = orderService.updateOrder(orderId, orderRequest);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/orders/products")
    public ResponseEntity<OrderLineDTO> addProductToOrder(@RequestBody OrderLineRequest orderLineRequest) {
        OrderLineDTO orderLine = orderService.addProductToOrder(orderLineRequest);
        return new ResponseEntity<>(orderLine, HttpStatus.OK);
    }

    @DeleteMapping("/{order_id}")
    public ResponseEntity<HttpStatus> deleteOrderById(@PathVariable(name = "order_id") Long orderId) {
        orderService.deleteOrderById(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
