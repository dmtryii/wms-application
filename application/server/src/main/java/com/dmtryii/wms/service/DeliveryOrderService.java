package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.request.DeliveryOrderRequest;
import com.dmtryii.wms.dto.request.StateRequest;
import com.dmtryii.wms.exception.ResourceNotFoundException;
import com.dmtryii.wms.model.DeliveryOrder;
import com.dmtryii.wms.model.Employee;
import com.dmtryii.wms.model.Item;
import com.dmtryii.wms.model.Manager;
import com.dmtryii.wms.model.enums.EState;
import com.dmtryii.wms.repository.DeliveryOrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryOrderService {

    public static final Logger LOG = LoggerFactory.getLogger(DeliveryOrder.class);
    private final DeliveryOrderRepository deliveryOrderRepository;
    private final EmployeeService employeeService;
    private final ItemService itemService;
    private final ManagerService managerService;

    public DeliveryOrder createDeliveryOrder(DeliveryOrderRequest request, Principal principal) {
        Employee employee = employeeService.getEmployeeByPrincipal(principal);
        Item item = itemService.getItemById(request.getItemId());
        Integer amount = request.getAmount();

        DeliveryOrder deliveryOrder = new DeliveryOrder(
                item,
                employee,
                amount
        );

        return deliveryOrderRepository.save(deliveryOrder);
    }

    public DeliveryOrder updateDeliveryOrderState(Long deliveryOrderId,
                                                  StateRequest request,
                                                  Principal principal) {
        DeliveryOrder deliveryOrder = getById(deliveryOrderId);
        Manager manager = managerService.getManagerByPrincipal(principal);
        EState newState = request.getState();

        switch (newState) {
            case CONFIRMED -> deliveryOrder.setState(EState.CONFIRMED);
            case UNCONFIRMED -> deliveryOrder.setState(EState.UNCONFIRMED);
            default -> deliveryOrder.setState(EState.WAITING_FOR_CONFIRMATION);
        }
        deliveryOrder.setWhoChecked(manager);
        return deliveryOrderRepository.save(deliveryOrder);
    }

    public List<DeliveryOrder> getAllByState(StateRequest request) {
        return deliveryOrderRepository.findAllByStateEquals(request.getState());
    }

    public List<DeliveryOrder> getAllDeliveryOrders() {
        return deliveryOrderRepository.findAll();
    }

    public DeliveryOrder getById(Long id) {
        return deliveryOrderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("The delivery order not fount by id: " + id)
        );
    }

    public void deleteById(Long id) {
        DeliveryOrder deliveryOrder = getById(id);
        deliveryOrderRepository.delete(deliveryOrder);
        LOG.info("The delivery order from id {} was deleted", id);
    }
}
