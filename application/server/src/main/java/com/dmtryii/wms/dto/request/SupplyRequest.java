package com.dmtryii.wms.dto.request;

public record SupplyRequest(
        Long supplierId,
        Long itemId,
        int amount
) {
}
