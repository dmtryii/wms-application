package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.response.EmployeeDTO;
import com.dmtryii.wms.dto.response.ManagerDTO;
import com.dmtryii.wms.dto.response.SupplierDTO;
import com.dmtryii.wms.dto.request.EmployeeRequest;
import com.dmtryii.wms.dto.request.ManagerRequest;
import com.dmtryii.wms.dto.request.SupplierRequest;
import com.dmtryii.wms.exception.ResourceNotCreatedException;
import com.dmtryii.wms.exception.handle_exception.BadRequestRecorder;
import com.dmtryii.wms.model.Employee;
import com.dmtryii.wms.model.Manager;
import com.dmtryii.wms.model.Supplier;
import com.dmtryii.wms.service.EmployeeService;
import com.dmtryii.wms.service.ManagerService;
import com.dmtryii.wms.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final EmployeeService employeeService;
    private final SupplierService supplierService;
    private final ManagerService managerService;
    private final BadRequestRecorder errorRecorder;
    private final ModelMapper modelMapper;

    @PostMapping("/employees")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid EmployeeRequest employeeRequest,
                                                   Principal principal,
                                                   BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errors = errorRecorder.getRecordedErrors(bindingResult);
            throw new ResourceNotCreatedException(errors);
        }
        Employee employee = employeeService.createEmployee(employeeRequest, principal);
        return new ResponseEntity<>(
                map(employee),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/suppliers")
    public ResponseEntity<SupplierDTO> createSupplier(@RequestBody @Valid SupplierRequest supplierRequest,
                                                   Principal principal,
                                                   BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errors = errorRecorder.getRecordedErrors(bindingResult);
            throw new ResourceNotCreatedException(errors);
        }
        Supplier supplier = supplierService.createSupplier(supplierRequest, principal);
        return new ResponseEntity<>(
                map(supplier),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/managers")
    public ResponseEntity<ManagerDTO> createManager(@RequestBody @Valid ManagerRequest request,
                                                     Principal principal,
                                                     BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errors = errorRecorder.getRecordedErrors(bindingResult);
            throw new ResourceNotCreatedException(errors);
        }
        Manager manager = managerService.createManager(request, principal);
        return new ResponseEntity<>(
                map(manager),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees()
                .stream()
                .map(this::map)
                .toList();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/suppliers")
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers() {
        List<SupplierDTO> suppliers = supplierService.getAllSuppliers()
                .stream()
                .map(this::map)
                .toList();
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    @GetMapping("/managers")
    public ResponseEntity<List<ManagerDTO>> getAllManagers() {
        List<ManagerDTO> managers = managerService.getAllManagers()
                .stream()
                .map(this::map)
                .toList();
        return new ResponseEntity<>(managers, HttpStatus.OK);
    }

    private SupplierDTO map(Supplier supplier) {
        return modelMapper.map(supplier, SupplierDTO.class);
    }
    private EmployeeDTO map(Employee employee) {
        return modelMapper.map(employee, EmployeeDTO.class);
    }
    private ManagerDTO map(Manager manager) {
        return modelMapper.map(manager, ManagerDTO.class);
    }
}
