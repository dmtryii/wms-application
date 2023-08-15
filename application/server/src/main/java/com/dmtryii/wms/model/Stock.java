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
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(nullable = false)
    private int quantity;

    public Stock(Warehouse warehouse, Item item, int quantity) {
        this.warehouse = warehouse;
        this.item = item;
        this.quantity = quantity;
    }
}
