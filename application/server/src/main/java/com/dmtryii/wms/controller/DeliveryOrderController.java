package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.response.DeliveryOrderDTO;
import com.dmtryii.wms.dto.request.DeliveryOrderRequest;
import com.dmtryii.wms.dto.request.StateRequest;
import com.dmtryii.wms.exception.ResourceNotCreatedException;
import com.dmtryii.wms.exception.ResourceNotUpdatedException;
import com.dmtryii.wms.exception.handle_exception.BadRequestRecorder;
import com.dmtryii.wms.model.DeliveryOrder;
import com.dmtryii.wms.service.DeliveryOrderService;
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
@RequestMapping("api/delivery_orders")
public class DeliveryOrderController {
    private final DeliveryOrderService deliveryOrderService;
    private final BadRequestRecorder errorRecorder;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<DeliveryOrderDTO> create(@RequestBody @Valid DeliveryOrderRequest request,
                                                                Principal principal,
                                                                BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errors = errorRecorder.getRecordedErrors(bindingResult);
            throw new ResourceNotCreatedException(errors);
        }
        DeliveryOrder deliveryOrder = deliveryOrderService.createDeliveryOrder(request, principal);
        return new ResponseEntity<>(
                map(deliveryOrder),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/{delivery_order_id}/state")
    public ResponseEntity<DeliveryOrderDTO> updateState(@PathVariable("delivery_order_id") Long deliveryOrderId,
                                                        @RequestBody @Valid StateRequest request,
                                                        BindingResult bindingResult,
                                                        Principal principal) {
        if(bindingResult.hasErrors()) {
            String errors = errorRecorder.getRecordedErrors(bindingResult);
            throw new ResourceNotUpdatedException(errors);
        }
        DeliveryOrder deliveryOrder = deliveryOrderService.updateDeliveryOrderState(deliveryOrderId, request, principal);
        return new ResponseEntity<>(
                map(deliveryOrder),
                HttpStatus.OK
        );
    }

    @GetMapping("/state")
    public ResponseEntity<List<DeliveryOrderDTO>> getAllByState(@RequestBody @Valid StateRequest request) {

        List<DeliveryOrderDTO> deliveryOrders = deliveryOrderService.getAllByState(request)
                .stream()
                .map(this::map)
                .toList();

        return new ResponseEntity<>(deliveryOrders, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DeliveryOrderDTO>> getAll() {
        List<DeliveryOrderDTO> deliveryOrders = deliveryOrderService.getAllDeliveryOrders()
                .stream()
                .map(this::map)
                .toList();
        return new ResponseEntity<>(deliveryOrders, HttpStatus.OK);
    }

    @GetMapping("/{delivery_order_id}")
    public ResponseEntity<DeliveryOrderDTO> getById(@PathVariable("delivery_order_id") Long deliveryOrderId) {

        DeliveryOrder deliveryOrder = deliveryOrderService.getById(deliveryOrderId);
        return new ResponseEntity<>(
                map(deliveryOrder),
                HttpStatus.OK
        );
    }

    private DeliveryOrderDTO map(DeliveryOrder deliveryOrder) {
        return modelMapper.map(deliveryOrder, DeliveryOrderDTO.class);
    }
}
