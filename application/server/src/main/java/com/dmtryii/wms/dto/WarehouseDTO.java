package com.dmtryii.wms.dto;

import com.dmtryii.wms.model.Address;

public record WarehouseDTO(
        Long id,
        String name,
        Address address
) {

}
