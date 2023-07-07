package com.dmtryii.wms.dto.request;

public record EmployeeRequest(
        String username,
        Long warehouseId
) {
}
