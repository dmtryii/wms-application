package com.dmtryii.wms.dto.request;

public record OrderLineRequest(
        Long orderId,
        Long productId,
        int amount
) {
}
