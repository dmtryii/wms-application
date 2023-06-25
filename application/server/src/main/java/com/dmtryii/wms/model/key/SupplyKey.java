package com.dmtryii.wms.model.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class SupplyKey implements Serializable {
    @Column(name = "item_id")
    private Long itemId;
    @Column(name = "employee_id")
    private Long employeeId;
    @Column(name = "supplier_id")
    private Long supplierId;
}
