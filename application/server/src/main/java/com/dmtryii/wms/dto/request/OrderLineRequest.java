package com.dmtryii.wms.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class OrderLineRequest {
    private Long orderId;
    private Long productId;
    @Min(value = 1, message = "The amount must be greater than zero")
    private Integer amount;
}
