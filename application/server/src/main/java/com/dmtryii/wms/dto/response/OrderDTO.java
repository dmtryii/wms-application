package com.dmtryii.wms.dto.response;

import com.dmtryii.wms.model.enums.EState;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private Long id;
    private LocalDateTime dataOfOrder;
    private String details;
    private EState state;
    private UserDTO user;
}
