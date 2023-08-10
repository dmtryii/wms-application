package com.dmtryii.wms.dto.response;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CityDTO {
    private Long id;
    @NotEmpty(message = "city name should not be empty")
    private String name;
    @NotEmpty(message = "country name should not be empty")
    private String countryName;
}
