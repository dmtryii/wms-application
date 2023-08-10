package com.dmtryii.wms.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressCreateRequest {
    @Size(min = 2, max = 50, message = "streetName must contain more than 2 char and less than 50")
    private String streetName;
    @Size(min = 1, max = 30, message = "streetNumber must contain more than 1 char and less than 30")
    private String streetNumber;
    @Size(max = 200, message = "details must contain less than 200 char")
    private String details;
    private Long cityId;
}
