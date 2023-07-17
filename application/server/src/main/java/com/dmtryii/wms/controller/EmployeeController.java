package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.request.SupplyRequest;
import com.dmtryii.wms.model.Supply;
import com.dmtryii.wms.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/order_supply")
    public ResponseEntity<Supply> orderSupply(@RequestBody SupplyRequest supplyRequest,
                                              Principal principal) {
        Supply supply = employeeService.orderDelivery(supplyRequest, principal);
        return new ResponseEntity<>(supply, HttpStatus.OK);
    }

}
