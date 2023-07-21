package com.dmtryii.wms.model;

import com.dmtryii.wms.model.key.SupplyLineKey;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class SupplyLine {
    @EmbeddedId
    private SupplyLineKey supplyLineId = new SupplyLineKey();
    @ManyToOne
    @MapsId("supplyId")
    @JoinColumn(name = "supply_id")
    private Supply supply;
    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @Column(nullable = false)
    private Integer amount;
    @Column(name = "date_of_addition")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfAddition;

    public SupplyLine(Supply supply, Item item, Employee employee, Integer amount) {
        this.supply = supply;
        this.item = item;
        this.employee = employee;
        this.amount = amount;
        this.dateOfAddition = LocalDate.now();
    }
}
