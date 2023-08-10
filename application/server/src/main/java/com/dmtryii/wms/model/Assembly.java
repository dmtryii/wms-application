package com.dmtryii.wms.model;

import com.dmtryii.wms.model.key.AssemblyKey;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Assembly {

    @EmbeddedId
    private AssemblyKey assemblyKeyId = new AssemblyKey();

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(nullable = false)
    private int amount;

    public Assembly(Product product, Item item, int amount) {
        this.product = product;
        this.item = item;
        this.amount = amount;
    }
}
