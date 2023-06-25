package com.dmtryii.wms.model;

import com.dmtryii.wms.model.key.StockKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Stock {
    @EmbeddedId
    private StockKey stockId = new StockKey();
    @ManyToOne
    @MapsId("warehouseId")
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Item item;
    @Column(nullable = false)
    private int quantity;
}
