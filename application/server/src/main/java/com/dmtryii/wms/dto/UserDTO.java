package com.dmtryii.wms.dto;

import com.dmtryii.wms.model.City;
import com.dmtryii.wms.model.enums.ERole;

import java.time.LocalDateTime;
import java.util.Set;

public record UserDTO(
        Long id,
        String username,
        String email,
        String phone,
        String address,
        String firstName,
        String lastName,
        String bio,
        LocalDateTime createData,
        Set<ERole> roles,
        City city
) {
}
