package com.dmtryii.wms.controller;

import com.dmtryii.wms.model.OrderLine;
import com.dmtryii.wms.repository.OrderLineRepository;
import com.dmtryii.wms.repository.OrderRepository;
import com.dmtryii.wms.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order_line")
public class OrderLineController {
    @Autowired
    private OrderLineRepository orderLineRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("{order_id}/order/{product_id}/product")
    public ResponseEntity<OrderLine> createOrderLine(@PathVariable(name = "order_id") Long orderId,
                                                     @PathVariable(name = "product_id") Long productId) {

        OrderLine _orderLine = new OrderLine(
                orderRepository.findById(orderId).orElseThrow(),
                productRepository.findById(productId).orElseThrow()
        );

        return new ResponseEntity<>(orderLineRepository.save(_orderLine), HttpStatus.CREATED);
    }

}
