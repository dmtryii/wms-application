package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.EmployeeDTO;
import com.dmtryii.wms.dto.SupplierDTO;
import com.dmtryii.wms.dto.request.EmployeeRequest;
import com.dmtryii.wms.dto.request.SupplierRequest;
import com.dmtryii.wms.dto_mapper.EmployeeDTOMapper;
import com.dmtryii.wms.dto_mapper.SupplierDTOMapper;
import com.dmtryii.wms.model.Employee;
import com.dmtryii.wms.model.Supplier;
import com.dmtryii.wms.service.EmployeeService;
import com.dmtryii.wms.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final EmployeeService employeeService;
    private final SupplierService supplierService;
    private final EmployeeDTOMapper employeeDTOMapper;
    private final SupplierDTOMapper supplierDTOMapper;

    @PostMapping("/employee")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeService.createEmployee(employeeRequest);
        return new ResponseEntity<>(
                employeeDTOMapper.apply(employee),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/supplier")
    public ResponseEntity<SupplierDTO> createSupplier(@RequestBody SupplierRequest supplierRequest) {
        Supplier supplier = supplierService.createSupplier(supplierRequest);
        return new ResponseEntity<>(
                supplierDTOMapper.apply(supplier),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/suppliers")
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers() {
        List<SupplierDTO> suppliers = supplierService.getAllSuppliers();
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }
}
