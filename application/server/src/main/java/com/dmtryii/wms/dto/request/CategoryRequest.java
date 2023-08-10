package com.dmtryii.wms.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequest {
    @NotEmpty(message = "category name should not be empty")
    @Size(min = 2, max = 30, message = "category name should be between 2 and 30 characters")
    private String name;
    private String description;
}
