package com.dmtryii.wms.dto.response;

import lombok.Data;

@Data
public class EmployeeDTO {
    private Long id;
    private UserDTO user;
    private WarehouseDTO warehouse;
}
