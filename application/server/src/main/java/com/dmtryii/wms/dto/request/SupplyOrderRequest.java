package com.dmtryii.wms.dto.request;

import lombok.Data;

@Data
public class SupplyOrderRequest {
    private Long companyId;
    private Long deliveryOrderId;
    private String details;
}
