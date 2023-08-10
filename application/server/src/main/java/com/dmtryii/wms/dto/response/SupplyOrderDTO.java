package com.dmtryii.wms.dto.response;

import lombok.Data;

@Data
public class SupplyOrderDTO {
    private CompanyDTO company;
    private ManagerDTO manager;
    private DeliveryOrderDTO deliveryOrder;
    private String details;
}
