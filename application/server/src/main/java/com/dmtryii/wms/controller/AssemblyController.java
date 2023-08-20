package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.request.AssemblyRequest;
import com.dmtryii.wms.dto.response.AssemblyDTO;
import com.dmtryii.wms.dto.response.ItemDTO;
import com.dmtryii.wms.exception.ResourceNotCreatedException;
import com.dmtryii.wms.exception.handle_exception.BadRequestRecorder;
import com.dmtryii.wms.model.Assembly;
import com.dmtryii.wms.model.Item;
import com.dmtryii.wms.service.AssemblyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/assemblies")
public class AssemblyController {
    private final AssemblyService assemblyService;
    private final BadRequestRecorder errorRecorder;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<AssemblyDTO> addAssemblyInstructions(@RequestBody @Valid AssemblyRequest assemblyRequest,
                                                               BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errors = errorRecorder.getRecordedErrors(bindingResult);
            throw new ResourceNotCreatedException(errors);
        }
        Assembly assembly = assemblyService.addAssemblyInstructions(assemblyRequest);
        return new ResponseEntity<>(
                map(assembly),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/products/{product_id}")
    public ResponseEntity<List<ItemDTO>> getProductAssembly(@PathVariable(name = "product_id") Long productId) {
        List<ItemDTO> items = assemblyService.getProductAssembly(productId)
                .stream()
                .map(this::map)
                .toList();
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

    private AssemblyDTO map(Assembly assembly) {
        return modelMapper.map(assembly, AssemblyDTO.class);
    }

    private ItemDTO map(Item item) {
        return modelMapper.map(item, ItemDTO.class);
    }
}
