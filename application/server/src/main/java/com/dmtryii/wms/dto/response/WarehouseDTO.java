package com.dmtryii.wms.dto.response;

import lombok.Data;

@Data
public class WarehouseDTO {
    private Long id;
    private String name;
    private AddressDTO address;
}
