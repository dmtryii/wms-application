package com.dmtryii.wms.dto;

import java.time.LocalDate;

public record OrderDTO(
        Long id,
        LocalDate dataOfOrder,
        String details,
        UserDTO user
) {
}
