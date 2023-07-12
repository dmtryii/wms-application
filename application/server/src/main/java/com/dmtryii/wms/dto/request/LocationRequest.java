package com.dmtryii.wms.dto.request;

public record LocationRequest (
        Long warehouseId,
        Long productId,
        int quantity
){
}
