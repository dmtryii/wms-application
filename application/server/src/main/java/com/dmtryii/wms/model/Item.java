package com.dmtryii.wms.model;

import com.dmtryii.wms.annotations.Price;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(nullable = false)
    @NotNull
    @NotEmpty(message = "item name should not be empty")
    private String name;

    @Column(nullable = false)
    @NotNull
    @Price
    private BigDecimal price;

    @Column(columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Set<Stock> stock;

    @OneToMany(mappedBy = "item")
    private Set<DeliveryOrder> deliveryOrders;
}
