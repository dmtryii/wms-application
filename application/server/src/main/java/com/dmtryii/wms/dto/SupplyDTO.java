package com.dmtryii.wms.dto;

import java.time.LocalDate;

public record SupplyDTO(
        Long id,
        EmployeeDTO employee,
        SupplierDTO supplier,
        boolean state,
        LocalDate dateOfSupply
) {
}
