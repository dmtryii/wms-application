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
public class StockKey implements Serializable {

    @Column(name = "warehouse_id")
    private Long warehouseId;

    @Column(name = "item_id")
    private Long itemId;
}
