package com.dmtryii.wms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    @NonNull
    private String name;
    private String description;
}
