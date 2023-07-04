package com.dmtryii.wms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    @NonNull
    private String username;
    @NonNull
    private String warehouseName;
}
