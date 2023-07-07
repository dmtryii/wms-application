package com.dmtryii.wms.dto_mapper;

import com.dmtryii.wms.dto.EmployeeDTO;
import com.dmtryii.wms.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class EmployeeDTOMapper implements Function<Employee, EmployeeDTO> {
    private final UserDTOMapper userDTOMapper;

    @Override
    public EmployeeDTO apply(Employee employee) {
        return new EmployeeDTO(
                employee.getId(),
                userDTOMapper.apply(employee.getUser()),
                employee.getWarehouse()
        );
    }
}
