package com.dmtryii.wms.service;

import com.dmtryii.wms.model.*;
import com.dmtryii.wms.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final SupplyOrderService supplyOrderService;
    private final WarehouseService warehouseService;
    private final ItemService itemService;

    public List<Stock> updateItemsByWarehouseId(Long warehouseId) {

        List<SupplyOrder> supplyOrders = supplyOrderService.getAllArrivedByWarehouseId(warehouseId);

        List<Stock> stocks = new ArrayList<>();

        for (SupplyOrder supplyOrder: supplyOrders) {

            DeliveryOrder deliveryOrder = supplyOrder.getDeliveryOrder();
            Long itemId = deliveryOrder.getItem().getId();
            Integer quantity = deliveryOrder.getAmount();

            stocks.add(create(warehouseId, itemId, quantity));
        }
        return stocks;
    }

    public Stock create(Long warehouseId, Long itemId, Integer quantity) {
        Warehouse warehouse = warehouseService.getWarehouseById(warehouseId);
        Item item = itemService.getItemById(itemId);

        Stock stock = new Stock(
                warehouse,
                item,
                quantity
        );
        return stockRepository.save(stock);
    }

    public List<Stock> getAll() {
        return stockRepository.findAll();
    }
}
