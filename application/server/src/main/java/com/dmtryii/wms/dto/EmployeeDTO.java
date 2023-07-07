package com.dmtryii.wms.dto;

import com.dmtryii.wms.model.Warehouse;

public record EmployeeDTO(
        Long id,
        UserDTO user,
        Warehouse warehouse
) {

}
