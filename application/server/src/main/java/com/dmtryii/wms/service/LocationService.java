package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.request.DoAssemblyRequest;
import com.dmtryii.wms.exception.DoAssemblyProductException;
import com.dmtryii.wms.model.*;
import com.dmtryii.wms.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    public static final Logger LOG = LoggerFactory.getLogger(Location.class);
    private final LocationRepository locationRepository;
    private final ProductService productService;
    private final WarehouseService warehouseService;
    private final AssemblyService assemblyService;
    private final StockService stockService;

    public Location doAssemblyProduct(Long warehouseId, DoAssemblyRequest request) {

        Long productId = request.getProductId();
        Integer productQuantity = request.getQuantity();

        List<Assembly> assemblies = assemblyService.getAssemblyByProductId(productId);

        for(Assembly assembly: assemblies) {

            Item item = assembly.getItem();
            int amount = assembly.getAmount() * productQuantity;

            Stock stock = stockService.getStock(warehouseId, item.getId());

            int itemQuantityInStock = stock.getQuantity();

            if(itemQuantityInStock < amount) {
                throw new DoAssemblyProductException("Not enough items to assembly a product");
            }
            stock.setQuantity(itemQuantityInStock - amount);
        }

        Location _location = getLocation(
                warehouseId,
                productId
        );

        if(_location != null) {
            updateQuantityProductsInWarehouse(_location, request.getQuantity());
            return locationRepository.save(_location);
        }

        Location location = new Location(
                warehouseService.getWarehouseById(warehouseId),
                productService.getProductById(productId),
                request.getQuantity()
        );
        return locationRepository.save(location);
    }

    public List<Location> getAllLocation() {
        return locationRepository.findAll();
    }

    public Location getLocation(Long warehouseId, Long productId) {
        return locationRepository
                .findLocationByWarehouseIdAndProductId(warehouseId, productId);
    }

    private void updateQuantityProductsInWarehouse(Location location, int quantity) {
        int newAmount = location.getQuantity() + quantity;

        if(newAmount < 0) {
            throw new RuntimeException();
        }
        location.setQuantity(newAmount);
        locationRepository.save(location);
    }
}
