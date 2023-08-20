package com.dmtryii.wms.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class AssemblyRequest {
    private Long productId;
    private Long itemId;
    @Min(value = 1, message = "The amount must be greater than zero")
    private Integer amount;
}
