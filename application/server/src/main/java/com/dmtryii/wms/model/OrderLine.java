package com.dmtryii.wms.model;

import com.dmtryii.wms.model.key.OrderLineKey;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @Min(value = 1, message = "The amount must be greater than zero")
    private Integer amount;

    @Column(name = "delivery_time")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deliveryTime;

    public OrderLine(Order order, Product product, Integer amount) {
        this.order = order;
        this.product = product;
        this.amount = amount;
    }
}
