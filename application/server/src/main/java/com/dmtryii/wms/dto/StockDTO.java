package com.dmtryii.wms.dto;

import com.dmtryii.wms.dto.response.ItemDTO;
import com.dmtryii.wms.dto.response.WarehouseDTO;
import lombok.Data;

@Data
public class StockDTO {
    private WarehouseDTO warehouse;
    private ItemDTO item;
    private Integer quantity;
}
