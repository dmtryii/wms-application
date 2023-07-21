package com.dmtryii.wms.dto;

import com.dmtryii.wms.model.Item;

import java.time.LocalDate;

public record SupplyLineDTO(
        SupplyDTO supply,
        Item item,
        EmployeeDTO employee,
        Integer amount,
        LocalDate dateOfAddition
) {
}
