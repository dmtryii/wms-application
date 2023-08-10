package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.response.ItemDTO;
import com.dmtryii.wms.dto.request.ItemRequest;
import com.dmtryii.wms.exception.ResourceNotCreatedException;
import com.dmtryii.wms.exception.ResourceNotUpdatedException;
import com.dmtryii.wms.exception.handle_exception.BadRequestRecorder;
import com.dmtryii.wms.model.Item;
import com.dmtryii.wms.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final ModelMapper modelMapper;
    private final BadRequestRecorder errorRecorder;

    @GetMapping
    public ResponseEntity<List<ItemDTO>> getAllItem() {
        List<ItemDTO> items = itemService.getAllItem()
                .stream()
                .map(this::map)
                .toList();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{item_id}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable(name = "item_id") Long itemId) {
        Item item = itemService.getItemById(itemId);
        return new ResponseEntity<>(
                map(item),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<ItemDTO> createItem(@RequestBody @Valid ItemRequest request,
                                              BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errors = errorRecorder.getRecordedErrors(bindingResult);
            throw new ResourceNotCreatedException(errors);
        }
        Item item = itemService.createItem(request);
        return new ResponseEntity<>(
                map(item),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{item_id}")
    public ResponseEntity<ItemDTO> updateItem(@PathVariable(name = "item_id") Long itemId,
                                              @RequestBody @Valid ItemRequest request,
                                              BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errors = errorRecorder.getRecordedErrors(bindingResult);
            throw new ResourceNotUpdatedException(errors);
        }
        Item item = itemService.updateItem(itemId, request);
        return new ResponseEntity<>(
                map(item),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{item_id}")
    public ResponseEntity<HttpStatus> deleteItemById(@PathVariable(name = "item_id") Long itemId) {
        itemService.deleteItemById(itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private ItemDTO map(Item item) {
        return modelMapper.map(item, ItemDTO.class);
    }
}
