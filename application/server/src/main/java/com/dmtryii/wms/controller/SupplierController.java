package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.SupplyDTO;
import com.dmtryii.wms.dto.request.DaysRequest;
import com.dmtryii.wms.dto_mapper.SupplyDTOMapper;
import com.dmtryii.wms.model.Supplier;
import com.dmtryii.wms.model.Supply;
import com.dmtryii.wms.service.SupplierService;
import com.dmtryii.wms.service.SupplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/suppliers")
public class SupplierController {
    private final SupplierService supplierService;
    private final SupplyService supplyService;
    private final SupplyDTOMapper supplyDTOMapper;

    @PatchMapping("/supplies/{supply_id}/confirm")
    public ResponseEntity<SupplyDTO> confirmSupply(@PathVariable(name = "supply_id") Long supplyId,
                                                   @RequestBody DaysRequest request) {
        Supply supply = supplyService.confirmSupply(supplyId, request.days());
        return new ResponseEntity<>(
                supplyDTOMapper.apply(supply),
                HttpStatus.OK
        );
    }

    @GetMapping("/supplies/not_confirmed")
    public ResponseEntity<List<SupplyDTO>> getNotConfirmSupply(Principal principal) {
        Supplier supplier = supplierService.getSupplierByPrincipal(principal);

        List<SupplyDTO> supplies = supplyService.getAllNotConfirmSupply(supplier).stream()
                .map(supplyDTOMapper)
                .toList();
        return new ResponseEntity<>(supplies, HttpStatus.OK);
    }

    @GetMapping("/supplies/confirmed")
    public ResponseEntity<List<SupplyDTO>> getConfirmSupply(Principal principal) {
        Supplier supplier = supplierService.getSupplierByPrincipal(principal);

        List<SupplyDTO> supplies = supplyService.getAllConfirmSupply(supplier).stream()
                .map(supplyDTOMapper)
                .toList();
        return new ResponseEntity<>(supplies, HttpStatus.OK);
    }
}
