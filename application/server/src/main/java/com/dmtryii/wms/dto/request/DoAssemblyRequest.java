package com.dmtryii.wms.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class DoAssemblyRequest {
    private Long productId;
    @Min(value = 1, message = "The quantity must be greater than zero")
    private Integer quantity;
}
