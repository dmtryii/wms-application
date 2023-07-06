package com.dmtryii.wms.dto.request;

import java.util.List;

public record ContactsUpdateRequest(
        Long id,
        String phone,
        String firstName,
        String lastName,
        String bio,
        List<String> socialNetworks
) {
}
