package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.request.StateRequest;
import com.dmtryii.wms.dto.request.SupplyOrderRequest;
import com.dmtryii.wms.exception.ResourceNotFoundException;
import com.dmtryii.wms.model.*;
import com.dmtryii.wms.model.enums.EState;
import com.dmtryii.wms.repository.SupplyOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class SupplyOrderService {

    private final SupplyOrderRepository supplyOrderRepository;
    private final ManagerService managerService;
    private final SupplierService supplierService;
    private final CompanyService companyService;
    private final DeliveryOrderService deliveryOrderService;

    public SupplyOrder createSupplyOrder(SupplyOrderRequest request, Principal principal) {
        Manager manager = managerService.getManagerByPrincipal(principal);
        Company company = companyService.getById(request.getCompanyId());
        DeliveryOrder deliveryOrder = deliveryOrderService.getById(request.getDeliveryOrderId());

        SupplyOrder supplyOrder = new SupplyOrder(
                company,
                manager,
                deliveryOrder
        );
        supplyOrder.setDetails(request.getDetails());
        return supplyOrderRepository.save(supplyOrder);
    }

    public SupplyOrder updateState(Long supplyOrderId, StateRequest request, Principal principal) {

        SupplyOrder supplyOrder = getById(supplyOrderId);
        Supplier supplier = supplierService.getSupplierByPrincipal(principal);
        EState newState = request.getState();

        supplyOrder.setWhoChecked(supplier);
        supplyOrder.setState(newState);

        return supplyOrderRepository.save(supplyOrder);
    }

    public SupplyOrder getById(Long id) {
        return supplyOrderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("The supply order not fount by id: " + id)
        );
    }
}
