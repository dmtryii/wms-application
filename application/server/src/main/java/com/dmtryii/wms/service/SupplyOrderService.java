package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.request.StateAndDaysRequest;
import com.dmtryii.wms.dto.request.SupplyOrderRequest;
import com.dmtryii.wms.exception.ResourceNotFoundException;
import com.dmtryii.wms.model.*;
import com.dmtryii.wms.model.enums.EState;
import com.dmtryii.wms.repository.SupplyOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplyOrderService {

    private final SupplyOrderRepository supplyOrderRepository;
    private final ManagerService managerService;
    private final SupplierService supplierService;
    private final CompanyService companyService;
    private final WarehouseService warehouseService;
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

    public SupplyOrder updateState(Long supplyOrderId, StateAndDaysRequest request, Principal principal) {

        SupplyOrder supplyOrder = getById(supplyOrderId);
        Supplier supplier = supplierService.getSupplierByPrincipal(principal);
        EState newState = request.getState();

        supplyOrder.setWhoChecked(supplier);
        supplyOrder.setState(newState);

        if(newState.equals(EState.CONFIRMED)) {
            confirmSupplyOrder(supplyOrder, request.getDays());
        }

        return supplyOrderRepository.save(supplyOrder);
    }

    public List<SupplyOrder> getAllArrivedByWarehouseId(Long warehouseId) {

        List<SupplyOrder> supplyOrders = getAllByWarehouseId(warehouseId)
                .stream()
                .filter(so -> so.getState().equals(EState.CONFIRMED))
                .filter(so -> LocalDateTime.now().isAfter(so.getDeliveryTime()))
                .toList();

        supplyOrders.forEach(so -> so.setState(EState.ARRIVED));
        return supplyOrders;
    }

    public List<SupplyOrder> getAllByWarehouseId(Long warehouseId) {
        Warehouse warehouse = warehouseService.getWarehouseById(warehouseId);
        return supplyOrderRepository.findAllByDeliveryOrder_Employee_Warehouse(warehouse);
    }

    public SupplyOrder getById(Long id) {
        return supplyOrderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("The supply order not fount by id: " + id)
        );
    }

    private void confirmSupplyOrder(SupplyOrder supplyOrder, Integer days) {
        supplyOrder.setDeliveryTime(
                LocalDateTime.now().plusDays(days)
        );
    }
}
