package com.dmtryii.wms.dto.response;

public record AssemblyItemsResponse(
        Long id,
        String name,
        Double price,
        String description,
        Integer amount
) {
}
