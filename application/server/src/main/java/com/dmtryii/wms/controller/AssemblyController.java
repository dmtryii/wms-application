package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.request.AssemblyRequest;
import com.dmtryii.wms.dto.response.AssemblyResponse;
import com.dmtryii.wms.dto_mapper.AssemblyDTOMapper;
import com.dmtryii.wms.model.Assembly;
import com.dmtryii.wms.model.Item;
import com.dmtryii.wms.service.AssemblyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/assemblies")
public class AssemblyController {
    private final AssemblyService assemblyService;
    private final AssemblyDTOMapper assemblyDTOMapper;

    @PostMapping
    public ResponseEntity<AssemblyResponse> addAssemblyInstructions(@RequestBody AssemblyRequest assemblyRequest) {
        Assembly assembly = assemblyService.addAssemblyInstructions(assemblyRequest);
        return new ResponseEntity<>(
                assemblyDTOMapper.apply(assembly),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/products/{product_id}")
    public ResponseEntity<List<Item>> getProductAssembly(@PathVariable(name = "product_id") Long productId) {
        List<Item> items = assemblyService.getProductAssembly(productId);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @DeleteMapping("/products/{product_id}/items/{item_id}")
    public ResponseEntity<HttpStatus> deleteAssemblyInstructions(@PathVariable(name = "product_id") Long productId,
                                                                 @PathVariable(name = "item_id") Long itemId) {
        assemblyService.deleteAssemblyInstructions(productId, itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/products/{product_id}")
    public ResponseEntity<HttpStatus> deleteAssemblyByProductId(@PathVariable(name = "product_id") Long productId) {
        assemblyService.deleteAssemblyInstructionsByProductId(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
