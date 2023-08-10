package com.dmtryii.wms.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class DeliveryOrderRequest {
    private Long itemId;
    @Min(value = 1, message = "The amount must be greater than zero")
    private Integer amount;
    private String details;
}
