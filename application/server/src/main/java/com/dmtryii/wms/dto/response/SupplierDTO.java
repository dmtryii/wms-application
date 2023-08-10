package com.dmtryii.wms.dto.response;

import lombok.Data;

@Data
public class SupplierDTO {
    private Long id;
    private UserDTO user;
    private CompanyDTO company;
}
