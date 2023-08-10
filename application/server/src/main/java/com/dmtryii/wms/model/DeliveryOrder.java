package com.dmtryii.wms.model;

import com.dmtryii.wms.model.enums.EState;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class DeliveryOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(nullable = false)
    @Min(value = 1, message = "The amount must be greater than zero")
    private Integer amount;

    @Enumerated(EnumType.STRING)
    private EState state;

    @Column(name = "time_of_addition")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime timeOfAddition;

    @ManyToOne
    @JoinColumn(name = "who_checked_id")
    private Manager whoChecked;

    @Column(columnDefinition = "text")
    private String details;

    @OneToMany(mappedBy = "deliveryOrder")
    private Set<SupplyOrder> supplyOrders;

    public DeliveryOrder(Item item, Employee employee, Integer amount) {
        this.item = item;
        this.employee = employee;
        this.amount = amount;
        this.state = EState.WAITING_FOR_CONFIRMATION;
        this.timeOfAddition = LocalDateTime.now();
    }
}
