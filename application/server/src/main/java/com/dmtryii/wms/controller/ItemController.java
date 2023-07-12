package com.dmtryii.wms.controller;

import com.dmtryii.wms.model.Item;
import com.dmtryii.wms.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<List<Item>> getAllItem() {
        List<Item> items = itemService.getAllItem();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{item_id}")
    public ResponseEntity<Item> getItemById(@PathVariable(name = "item_id") Long itemId) {
        Item item = itemService.getItemById(itemId);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item itemRequest) {
        Item item = itemService.createItem(itemRequest);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("{item_id}")
    public ResponseEntity<Item> updateItem(@PathVariable(name = "item_id") Long itemId,
                                           @RequestBody Item itemRequest) {
        Item item = itemService.updateItem(itemId, itemRequest);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping("/{item_id}")
    public ResponseEntity<HttpStatus> deleteItemById(@PathVariable(name = "item_id") Long itemId) {
        itemService.deleteItemById(itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
