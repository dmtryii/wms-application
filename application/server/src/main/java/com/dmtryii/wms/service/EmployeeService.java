package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.EmployeeDTO;
import com.dmtryii.wms.dto.request.EmployeeRequest;
import com.dmtryii.wms.dto_mapper.EmployeeDTOMapper;
import com.dmtryii.wms.model.*;
import com.dmtryii.wms.model.enums.ERole;
import com.dmtryii.wms.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService  {
    public static final Logger LOG = LoggerFactory.getLogger(Employee.class);
    private final EmployeeRepository employeeRepository;
    private final UserService userService;
    private final WarehouseService warehouseService;
    private final EmployeeDTOMapper employeeDTOMapper;

    public Employee createEmployee(EmployeeRequest employeeRequest) {

        String username = employeeRequest.username();

        Employee _employee = employeeRepository
                .findEmployeeByUserUsername(username);
        Warehouse warehouse = warehouseService
                .getWarehouseById(employeeRequest.warehouseId());

        if(_employee != null) {
            _employee.setWarehouse(warehouse);
            return employeeRepository.save(_employee);
        }

        User user = userService.getUserByUsername(username);
        user.getRoles().add(ERole.EMPLOYEE);

        Employee employee = new Employee();
        employee.setUser(user);
        employee.setWarehouse(warehouse);
        return employeeRepository.save(employee);
    }

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeDTOMapper)
                .collect(Collectors.toList());
    }

    public Employee getEmployeeByPrincipal(Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        return employeeRepository.findEmployeeByUser(user);
    }
}
