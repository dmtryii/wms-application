package com.dmtryii.wms.dto;

import com.dmtryii.wms.model.Product;

public record OrderLineDTO (
        OrderDTO order,
        Product product,
        int amount
) {
}
