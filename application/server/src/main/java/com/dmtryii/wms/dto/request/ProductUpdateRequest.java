package com.dmtryii.wms.dto.request;

import com.dmtryii.wms.annotations.Price;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateRequest {
    @NotNull
    @NotEmpty(message = "product name should not be empty")
    @Size(min = 2, max = 30, message = "product name should be between 2 and 30 characters")
    private String name;
    @NotNull
    @Price
    private BigDecimal price;
    private String description;
    private Long categoryId;
}
