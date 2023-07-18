package com.dmtryii.wms.dto.request;

public record AssemblyRequest(
        Long productId,
        Long itemId,
        int amount
) {
}
