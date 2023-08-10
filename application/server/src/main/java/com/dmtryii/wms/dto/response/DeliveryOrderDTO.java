package com.dmtryii.wms.dto.response;

import lombok.Data;

@Data
public class DeliveryOrderDTO {
    private Long id;
    private ItemDTO item;
    private EmployeeDTO employee;
    private Integer amount;
}
