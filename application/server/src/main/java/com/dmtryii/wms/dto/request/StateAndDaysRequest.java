package com.dmtryii.wms.dto.request;

import com.dmtryii.wms.model.enums.EState;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class StateAndDaysRequest {
    @Enumerated(EnumType.STRING)
    private EState state;
    @Min(value = 1, message = "The days must be greater than zero")
    private Integer days;
}
