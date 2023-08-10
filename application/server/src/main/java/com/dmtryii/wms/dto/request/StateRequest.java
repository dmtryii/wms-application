package com.dmtryii.wms.dto.request;

import com.dmtryii.wms.model.enums.EState;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class StateRequest {
    @Enumerated(EnumType.STRING)
    private EState state;
}
