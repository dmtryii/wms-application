package com.dmtryii.wms.dto_mapper;

import com.dmtryii.wms.dto.SupplyDTO;
import com.dmtryii.wms.model.Supply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class SupplyDTOMapper implements Function<Supply, SupplyDTO> {
    private final EmployeeDTOMapper employeeMapper;
    private final SupplierDTOMapper supplierMapper;

    @Override
    public SupplyDTO apply(Supply supply) {
        return new SupplyDTO(
                supply.getId(),
                employeeMapper.apply(supply.getEmployee()),
                supplierMapper.apply(supply.getSupplier()),
                supply.isState(),
                supply.getDateOfSupply()
        );
    }
}
