package com.dmtryii.wms.model;

import com.dmtryii.wms.model.enums.EState;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class SupplyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supply_order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @ManyToOne
    @JoinColumn(name = "delivery_order_id")
    private DeliveryOrder deliveryOrder;

    @Enumerated(EnumType.STRING)
    private EState state;

    @ManyToOne
    @JoinColumn(name = "who_checked_id")
    private Supplier whoChecked;

    @Column(name = "time_of_addition")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime timeOfAddition;

    @Column(name = "delivery_time")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime deliveryTime;

    @Column(columnDefinition = "text")
    private String details;

    public SupplyOrder(Company company, Manager manager, DeliveryOrder deliveryOrder) {
        this.company = company;
        this.manager = manager;
        this.deliveryOrder = deliveryOrder;
        this.timeOfAddition = LocalDateTime.now();
        this.state = EState.WAITING_FOR_CONFIRMATION;
    }
}
