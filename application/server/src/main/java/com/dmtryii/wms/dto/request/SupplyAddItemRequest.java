package com.dmtryii.wms.dto.request;

public record SupplyAddItemRequest(
        Long supplyId,
        Long itemId,
        int amount
) {
}
