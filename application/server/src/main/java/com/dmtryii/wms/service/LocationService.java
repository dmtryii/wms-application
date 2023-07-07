package com.dmtryii.wms.service;

import com.dmtryii.wms.model.Location;
import com.dmtryii.wms.model.OrderLine;
import com.dmtryii.wms.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    public static final Logger LOG = LoggerFactory.getLogger(OrderLine.class);
    private final LocationRepository locationRepository;
    private final ProductService productService;
    private final WarehouseService warehouseService;

    public List<Location> getAllLocation() {
        return locationRepository.findAll();
    }

    public Location getLocation(Long warehouseId, Long productId) {
        return locationRepository
                .findLocationByWarehouseIdAndProductId(warehouseId, productId);
    }

    public Location addProductToWarehouse(Long warehouseId, Long productId, int quantity) {

        Location _location = getLocation(warehouseId, productId);

        if(_location != null) {
            updateQuantityProductsInWarehouse(_location, quantity);
            return locationRepository.save(_location);
        }

        Location location = new Location();
        location.setWarehouse(warehouseService.getWarehouseById(warehouseId));
        location.setProduct(productService.getProductById(productId));
        location.setQuantity(quantity);

        return locationRepository.save(location);
    }

    public int checkAvailabilityProductInWarehouse(Long warehouseId, Long productId) {
        Location location = locationRepository
                .findLocationByWarehouseIdAndProductId(warehouseId, productId);
        return location.getQuantity();
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
