package com.dmtryii.wms.model;

import com.dmtryii.wms.model.key.SupplyKey;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Supply {
    @EmbeddedId
    private SupplyKey supplyId = new SupplyKey();
    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @ManyToOne
    @MapsId("supplierId")
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    @Column(nullable = false)
    private int amount;
    @Column(name = "date_of_supply")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfSupply;
}
