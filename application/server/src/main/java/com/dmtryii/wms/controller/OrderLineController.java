package com.dmtryii.wms.controller;

import com.dmtryii.wms.model.OrderLine;
import com.dmtryii.wms.service.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order_line")
public class OrderLineController {

    @Autowired
    private OrderLineService orderLineService;

    @PostMapping("{order_id}/order/{product_id}/product/{amount}/amount")
    public ResponseEntity<OrderLine> createOrderLine(@PathVariable(name = "order_id") Long orderId,
                                                     @PathVariable(name = "product_id") Long productId,
                                                     @PathVariable(name = "amount") int amount) {

        OrderLine orderLine = orderLineService.addProductToOrder(
                orderId,
                productId,
                amount
        );

        return new ResponseEntity<>(orderLine, HttpStatus.CREATED);
    }

}
