package com.dmtryii.wms.dto;

import com.dmtryii.wms.model.Address;
import com.dmtryii.wms.model.Contacts;
import com.dmtryii.wms.model.enums.ERole;

import java.time.LocalDateTime;
import java.util.Set;

public record UserDTO(
        Long id,
        String username,
        Contacts contacts,
        Address address,
        LocalDateTime createData,
        Set<ERole> roles
) {
}
