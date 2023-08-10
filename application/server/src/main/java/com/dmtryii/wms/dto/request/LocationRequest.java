package com.dmtryii.wms.dto.request;

import lombok.Data;

@Data
public class LocationRequest {
    private Long warehouseId;
    private Long productId;
    private Integer quantity;
}
