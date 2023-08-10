package com.dmtryii.wms.dto.response;

import lombok.Data;

@Data
public class AddressDTO {
    private Long id;
    private String streetName;
    private String streetNumber;
    private String details;
    private CityDTO city;
}
