package com.dmtryii.wms.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeRequest {
    @NotEmpty(message = "username should not be empty")
    @Size(min = 2, max = 30, message = "username should be between 2 and 30 characters")
    private String username;
    private Long warehouseId;
}
