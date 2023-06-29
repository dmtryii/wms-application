package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.ItemDTO;
import com.dmtryii.wms.exception.NotFoundException;
import com.dmtryii.wms.model.Item;
import com.dmtryii.wms.model.OrderLine;
import com.dmtryii.wms.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    public static final Logger LOG = LoggerFactory.getLogger(OrderLine.class);
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item createItem(ItemDTO itemDTO) {
        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setPrice(itemDTO.getPrice());
        item.setDescription(itemDTO.getDescription());

        LOG.info("A new item has been created: {}", item.getName());
        return itemRepository.save(item);
    }

    public Item updateItem(Long itemId, ItemDTO itemDTO) {
        Item item = getItemById(itemId);
        item.setName(itemDTO.getName());
        item.setPrice(itemDTO.getPrice());
        item.setDescription(itemDTO.getDescription());

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
