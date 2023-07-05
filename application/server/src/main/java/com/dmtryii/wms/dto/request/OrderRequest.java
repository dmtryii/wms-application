package com.dmtryii.wms.dto.request;

public record OrderRequest(
        Long id,
        String username,
        String details
) {

}
