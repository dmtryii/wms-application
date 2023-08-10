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
public class AssemblyKey implements Serializable {

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "item_id")
    private Long itemId;
}
