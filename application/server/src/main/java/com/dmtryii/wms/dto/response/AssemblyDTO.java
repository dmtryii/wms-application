package com.dmtryii.wms.dto.response;

import lombok.Data;

@Data
public class AssemblyDTO {
    private ProductDTO product;
    private ItemDTO item;
    private Integer amount;
}
