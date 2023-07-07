package com.dmtryii.wms.service;

import com.dmtryii.wms.exception.NotFoundException;
import com.dmtryii.wms.model.Item;
import com.dmtryii.wms.model.OrderLine;
import com.dmtryii.wms.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    public static final Logger LOG = LoggerFactory.getLogger(OrderLine.class);
    private final ItemRepository itemRepository;

    public Item createItem(Item itemRequest) {
        Item item = new Item();
        item.setName(itemRequest.getName());
        item.setPrice(itemRequest.getPrice());
        item.setDescription(itemRequest.getDescription());

        LOG.info("A new item has been created: {}", item.getName());
        return itemRepository.save(item);
    }

    public Item updateItem(Long itemId, Item itemRequest) {
        Item item = getItemById(itemId);
        item.setName(itemRequest.getName());
        item.setPrice(itemRequest.getPrice());
        item.setDescription(itemRequest.getDescription());

        LOG.info("The item has been updated: {}", item.getName());
        return itemRepository.save(item);
    }

    public List<Item> getAllItem() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(
                () -> new NotFoundException(String.format("Item not found: %s", itemId))
        );
    }

    public void deleteItemById(Long itemId) {
        itemRepository.deleteById(itemId);
        LOG.info("The item has been deleted: {}", itemId);
    }
}
