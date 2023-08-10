package com.dmtryii.wms.dto.response;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CategoryDTO {
    private Long id;
    @NotEmpty(message = "category name should not be empty")
    private String name;
    private String description;
}
