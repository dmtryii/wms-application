package com.dmtryii.wms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class OrderLine {

    @EmbeddedId
    private OrderLineKey OrderLineId = new OrderLineKey();

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(nullable = false)
    private int amount;
    @Column(name = "delivery_time")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deliveryTime;

    public OrderLine(Order order, Product product, int amount) {
        this.order = order;
        this.product = product;
        this.amount = amount;
        deliveryTime = LocalDate.now().plusDays(3L);
    }
}
