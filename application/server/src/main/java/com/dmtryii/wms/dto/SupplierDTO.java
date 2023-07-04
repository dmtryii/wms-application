package com.dmtryii.wms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class SupplierDTO {
    @NonNull
    private String username;
    private String companyName;
}
