package com.dmtryii.wms.dto;

public record SupplierDTO(
        Long id,
        String companyName,
        Double rating,
        UserDTO user
) {
}
