package com.dmtryii.wms.service;

import com.dmtryii.wms.model.Location;
import com.dmtryii.wms.model.OrderLine;
import com.dmtryii.wms.repository.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    public static final Logger LOG = LoggerFactory.getLogger(OrderLine.class);
    private final LocationRepository locationRepository;
    private final ProductService productService;
    private final WarehouseService warehouseService;

    @Autowired
    public LocationService(LocationRepository locationRepository,
                           ProductService productService,
                           WarehouseService warehouseService) {
        this.locationRepository = locationRepository;
        this.productService = productService;
        this.warehouseService = warehouseService;
    }

    public List<Location> getAllLocation() {
        return locationRepository.findAll();
    }

    public Location getLocation(Long warehouseId, Long productId) {
        return locationRepository
                .findLocationByWarehouseIdAndProductId(warehouseId, productId);
    }

    public Location addProductToWarehouse(Long warehouseId, Long productId, int quantity) {

        Location existingLocation = getLocation(warehouseId, productId);

        if(existingLocation == null) {
            Location location = new Location();
            location.setWarehouse(warehouseService.getWarehouseById(warehouseId));
            location.setProduct(productService.getProductById(productId));
            location.setQuantity(quantity);

            return locationRepository.save(location);
        }

        updateQuantityProductsInWarehouse(existingLocation, quantity);
        return locationRepository.save(existingLocation);
    }

    public boolean checkAvailabilityProductInWarehouse(Long warehouseId, Long productId) {
        Location location = locationRepository
                .findLocationByWarehouseIdAndProductId(warehouseId, productId);
        return location.getQuantity() != 0;
    }

    private void updateQuantityProductsInWarehouse(Location location, int quantity) {
        int newAmount = location.getQuantity() + quantity;

        if(newAmount < 0) {
            return;
        }
        location.setQuantity(newAmount);
        locationRepository.save(location);
    }
}
