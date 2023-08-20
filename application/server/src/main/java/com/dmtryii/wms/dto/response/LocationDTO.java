package com.dmtryii.wms.dto.response;

import lombok.Data;

@Data
public class LocationDTO {
    private WarehouseDTO warehouse;
    private ProductDTO product;
    private Integer quantity;
}
