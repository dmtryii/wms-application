package com.dmtryii.wms.dto;

import com.dmtryii.wms.model.Address;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class WarehouseDTO {
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private Address address;
}
