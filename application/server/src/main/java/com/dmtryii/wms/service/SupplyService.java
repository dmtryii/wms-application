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
import java.util.List;

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

    public Supply confirmSupply(Long supplyId, int days) {
        Supply supply = getSupplyById(supplyId);
        supply.setState(true);
        supply.setDateOfSupply(LocalDate.now().plusDays(days));
        return supplyRepository.save(supply);
    }

    public List<Supply> getAllBySupplier(Supplier supplier) {
        return supplyRepository.findAllBySupplier(supplier);
    }

    public List<Supply> getAllConfirmSupply(Supplier supplier) {
        return getAllBySupplier(supplier).stream()
                .filter(Supply::isState)
                .toList();
    }

    public List<Supply> getAllNotConfirmSupply(Supplier supplier) {
        return getAllBySupplier(supplier).stream()
                .filter(supply -> !supply.isState())
                .toList();
    }

    public List<Supply> getAllConfirmSupply() {
        return getAllSupply().stream()
                .filter(Supply::isState)
                .toList();
    }

    public List<Supply> getAllNotConfirmSupply() {
        return getAllSupply().stream()
                .filter(supply -> !supply.isState())
                .toList();
    }

    public List<Supply> getAllSupply() {
        return supplyRepository.findAll();
    }

    public Supply getSupplyById(Long supplyId) {
        return supplyRepository.findById(supplyId).orElseThrow(
                () -> new RuntimeException("Supply not fount by id: "+ supplyId)
        );
    }
}
