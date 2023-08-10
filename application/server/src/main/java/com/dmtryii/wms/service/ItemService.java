package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.request.ItemRequest;
import com.dmtryii.wms.exception.ResourceNotFoundException;
import com.dmtryii.wms.model.Item;
import com.dmtryii.wms.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    public static final Logger LOG = LoggerFactory.getLogger(Item.class);
    private final ItemRepository itemRepository;

    public Item createItem(ItemRequest request) {
        Item item = Item.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .build();
        LOG.info("A new item has been created: {}", item.getName());
        return itemRepository.save(item);
    }

    public Item updateItem(Long itemId, ItemRequest request) {
        Item item = getItemById(itemId);
        item.setName(request.getName());
        item.setPrice(request.getPrice());
        item.setDescription(request.getDescription());

        LOG.info("The item has been updated: {}", item.getName());
        return itemRepository.save(item);
    }

    public List<Item> getAllItem() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(
                () -> new ResourceNotFoundException("Item not found: " + itemId)
        );
    }

    public void deleteItemById(Long itemId) {
        Item item = getItemById(itemId);
        itemRepository.delete(item);
        LOG.info("The item has been deleted: {}", itemId);
    }
}
