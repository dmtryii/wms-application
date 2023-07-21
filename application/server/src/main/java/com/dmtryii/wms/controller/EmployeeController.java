package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.SupplyDTO;
import com.dmtryii.wms.dto.SupplyLineDTO;
import com.dmtryii.wms.dto.request.SupplyAddItemRequest;
import com.dmtryii.wms.dto.request.SupplyCreateRequest;
import com.dmtryii.wms.dto_mapper.SupplyDTOMapper;
import com.dmtryii.wms.dto_mapper.SupplyLineDTOMapper;
import com.dmtryii.wms.model.Supply;
import com.dmtryii.wms.model.SupplyLine;
import com.dmtryii.wms.service.SupplyService;
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
    private final SupplyService supplyService;
    private final SupplyDTOMapper supplyDTOMapper;
    private final SupplyLineDTOMapper supplyLineDTOMapper;

    @PostMapping("/supplies")
    public ResponseEntity<SupplyDTO> createSupply(@RequestBody SupplyCreateRequest supplyRequest,
                                                  Principal principal) {
        Supply supply = supplyService.createSupply(supplyRequest, principal);
        return new ResponseEntity<>(
                supplyDTOMapper.apply(supply),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/supplies/items")
    public ResponseEntity<SupplyLineDTO> addItemToSupply(@RequestBody SupplyAddItemRequest request,
                                                         Principal principal) {
        SupplyLine supplyLine = supplyService.addItemToSupply(request, principal);
        return new ResponseEntity<>(
                supplyLineDTOMapper.apply(supplyLine),
                HttpStatus.OK
        );
    }
}
