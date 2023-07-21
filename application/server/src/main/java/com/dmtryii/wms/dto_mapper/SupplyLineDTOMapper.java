package com.dmtryii.wms.dto_mapper;

import com.dmtryii.wms.dto.SupplyLineDTO;
import com.dmtryii.wms.model.SupplyLine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class SupplyLineDTOMapper implements Function<SupplyLine, SupplyLineDTO> {
    private final SupplyDTOMapper supplyMapper;
    private final EmployeeDTOMapper employeeMapper;

    @Override
    public SupplyLineDTO apply(SupplyLine supplyLine) {
        return new SupplyLineDTO(
                supplyMapper.apply(supplyLine.getSupply()),
                supplyLine.getItem(),
                employeeMapper.apply(supplyLine.getEmployee()),
                supplyLine.getAmount(),
                supplyLine.getDateOfAddition()
        );
    }
}
