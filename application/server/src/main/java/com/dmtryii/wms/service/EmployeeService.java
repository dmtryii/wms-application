package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.EmployeeDTO;
import com.dmtryii.wms.model.Employee;
import com.dmtryii.wms.model.User;
import com.dmtryii.wms.model.Warehouse;
import com.dmtryii.wms.model.enums.ERole;
import com.dmtryii.wms.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService  {
    private final EmployeeRepository employeeRepository;
    private final UserService userService;
    private final WarehouseService warehouseService;

    public Employee createEmployee(EmployeeDTO employeeDTO) {

        String username = employeeDTO.getUsername();

        Employee _employee = employeeRepository.findEmployeeByUserUsername(username);
        Warehouse warehouse = warehouseService.getWarehouseByName(employeeDTO.getWarehouseName());

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

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
