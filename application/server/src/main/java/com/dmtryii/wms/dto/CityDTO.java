package com.dmtryii.wms.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class CityDTO {
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String country;
    private Double longitude;
    private Double latitude;
}
