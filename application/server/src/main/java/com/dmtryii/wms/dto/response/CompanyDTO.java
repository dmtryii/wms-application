package com.dmtryii.wms.dto.response;

import lombok.Data;

@Data
public class CompanyDTO {
    private Long id;
    private String name;
    private double rating;
    private AddressDTO address;
}
