package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.request.StateAndDaysRequest;
import com.dmtryii.wms.dto.response.SupplyOrderDTO;
import com.dmtryii.wms.dto.request.SupplyOrderRequest;
import com.dmtryii.wms.exception.ResourceNotCreatedException;
import com.dmtryii.wms.exception.ResourceNotUpdatedException;
import com.dmtryii.wms.exception.handle_exception.BadRequestRecorder;
import com.dmtryii.wms.model.SupplyOrder;
import com.dmtryii.wms.service.SupplyOrderService;
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
@RequiredArgsConstructor
@RequestMapping("api/supply_orders")
public class SupplyOrderController {
    private final SupplyOrderService supplyOrderService;
    private final BadRequestRecorder errorRecorder;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<SupplyOrderDTO> createSupplyOrder(@RequestBody SupplyOrderRequest request,
                                                            Principal principal,
                                                            BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errors = errorRecorder.getRecordedErrors(bindingResult);
            throw new ResourceNotCreatedException(errors);
        }
        SupplyOrder supplyOrder = supplyOrderService.createSupplyOrder(request, principal);
        return new ResponseEntity<>(
                map(supplyOrder),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/{supply_order_id}/state")
    public ResponseEntity<SupplyOrderDTO> updateState(@PathVariable("supply_order_id") Long supplyOrderId,
                                                      @RequestBody @Valid StateAndDaysRequest request,
                                                      Principal principal,
                                                      BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errors = errorRecorder.getRecordedErrors(bindingResult);
            throw new ResourceNotUpdatedException(errors);
        }
        SupplyOrder supplyOrder = supplyOrderService.updateState(supplyOrderId, request, principal);
        return new ResponseEntity<>(
                map(supplyOrder),
                HttpStatus.OK
        );
    }

    @GetMapping("warehouses/{warehouse_id}")
    public ResponseEntity<List<SupplyOrderDTO>> getAllByWarehouseId(@PathVariable("warehouse_id") Long warehouseId) {

        List<SupplyOrderDTO> supplyOrders = supplyOrderService.getAllByWarehouseId(warehouseId)
                .stream()
                .map(this::map)
                .toList();
        return new ResponseEntity<>(supplyOrders, HttpStatus.OK);
    }

    private SupplyOrderDTO map(SupplyOrder supplyOrder) {
        return modelMapper.map(supplyOrder, SupplyOrderDTO.class);
    }
}
