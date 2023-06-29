package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.ItemDTO;
import com.dmtryii.wms.model.Item;
import com.dmtryii.wms.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<List<Item>> getAllItem() {
        List<Item> items = itemService.getAllItem();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("{item_id}")
    public ResponseEntity<Item> getItemById(@PathVariable(name = "item_id") Long itemId) {
        Item item = itemService.getItemById(itemId);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody ItemDTO itemDTO) {
        Item item = itemService.createItem(itemDTO);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PostMapping("{item_id}")
    public ResponseEntity<Item> updateItem(@PathVariable(name = "item_id") Long itemId,
                                           @RequestBody ItemDTO itemDTO) {
        Item item = itemService.updateItem(itemId, itemDTO);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping("{item_id}")
    public ResponseEntity<HttpStatus> deleteItemById(@PathVariable(name = "item_id") Long itemId) {
        itemService.deleteItemById(itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
