package com.dmtryii.wms.dto;

import com.dmtryii.wms.model.Item;

import java.time.LocalDate;

public record SupplyDTO(
        Item item,
        EmployeeDTO employee,
        SupplierDTO supplier,
        int amount,
        LocalDate localDate
) {
}
