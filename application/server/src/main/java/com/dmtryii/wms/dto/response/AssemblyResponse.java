package com.dmtryii.wms.dto.response;

import com.dmtryii.wms.model.Product;

import java.util.List;

public record AssemblyResponse(
        Product product,
        List<AssemblyItemsResponse> items
) {
}
