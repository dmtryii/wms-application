package com.dmtryii.wms.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CompanyCreateRequest {
    @Size(min = 2, max = 30, message = "name must contain more than 2 char and less than 30")
    @NotEmpty(message = "name should not be empty")
    private String name;
}
