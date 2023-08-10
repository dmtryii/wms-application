package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.request.EmployeeRequest;
import com.dmtryii.wms.exception.RoleException;
import com.dmtryii.wms.model.*;
import com.dmtryii.wms.model.enums.ERole;
import com.dmtryii.wms.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService  {

    public static final Logger LOG = LoggerFactory.getLogger(Employee.class);
    private final EmployeeRepository employeeRepository;
    private final UserService userService;
    private final WarehouseService warehouseService;

    public Employee createEmployee(EmployeeRequest employeeRequest, Principal principal) {

        String username = employeeRequest.getUsername();
        Employee _employee = employeeRepository
                .findEmployeeByUserUsername(username);

        if(_employee != null) {
            throw new RoleException("The user already has this role");
        }

        User user = userService.getUserByUsername(username);
        Long warehouseId = employeeRequest.getWarehouseId();

        User whoCreated = userService.getUserByPrincipal(principal);
        user.getRoles().add(ERole.EMPLOYEE);

        Warehouse warehouse = warehouseService.getWarehouseById(warehouseId);

        Employee employee = Employee.builder()
                .warehouse(warehouse)
                .user(user)
                .whoCreated(whoCreated)
                .createData(LocalDateTime.now())
                .build();
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeByPrincipal(Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        return employeeRepository.findEmployeeByUser(user);
    }
}
