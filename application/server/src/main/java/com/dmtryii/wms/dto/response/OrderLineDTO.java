package com.dmtryii.wms.dto.response;

import lombok.Data;

@Data
public class OrderLineDTO {
    private OrderDTO order;
    private ProductDTO product;
    private Integer amount;
}
