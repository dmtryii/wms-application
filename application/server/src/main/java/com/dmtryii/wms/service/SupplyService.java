package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.request.SupplyAddItemRequest;
import com.dmtryii.wms.dto.request.SupplyCreateRequest;
import com.dmtryii.wms.model.*;
import com.dmtryii.wms.repository.SupplyLineRepository;
import com.dmtryii.wms.repository.SupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SupplyService {
    private final SupplyRepository supplyRepository;
    private final SupplyLineRepository supplyLineRepository;
    private final EmployeeService employeeService;
    private final SupplierService supplierService;
    private final ItemService itemService;

    public Supply createSupply(SupplyCreateRequest supplyRequest, Principal principal) {

        Employee employee = employeeService.getEmployeeByPrincipal(principal);
        Supplier supplier = supplierService.getSupplierById(supplyRequest.supplierId());

        Supply supply = Supply.builder()
                .employee(employee)
                .supplier(supplier)
                .dateOfSupply(LocalDate.now())
                .state(false)
                .build();

        return supplyRepository.save(supply);
    }

    public SupplyLine addItemToSupply(SupplyAddItemRequest addItemRequest, Principal principal) {

        Supply supply = getSupplyById(addItemRequest.supplyId());
        Item item = itemService.getItemById(addItemRequest.itemId());
        Employee employee = employeeService.getEmployeeByPrincipal(principal);

        SupplyLine supplyLine = new SupplyLine(
                supply,
                item,
                employee,
                addItemRequest.amount()
        );

        return supplyLineRepository.save(supplyLine);
    }

    public Supply getSupplyById(Long supplyId) {
        return supplyRepository.findById(supplyId).orElseThrow(
                () -> new RuntimeException("Supply not fount by id: "+ supplyId)
        );
    }
}
