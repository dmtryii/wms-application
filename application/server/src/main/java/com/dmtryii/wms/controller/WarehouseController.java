package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.request.AddressRequest;
import com.dmtryii.wms.dto.WarehouseDTO;
import com.dmtryii.wms.model.Location;
import com.dmtryii.wms.model.Warehouse;
import com.dmtryii.wms.service.LocationService;
import com.dmtryii.wms.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/warehouse")
@RequiredArgsConstructor
public class WarehouseController {
    private final WarehouseService warehouseService;
    private final LocationService locationService;

    @PostMapping("{warehouse_id}/product/{product_id}/quantity/{quantity}")
    public ResponseEntity<Location> addProductToWarehouse(@PathVariable(name = "warehouse_id") Long warehouseId,
                                                          @PathVariable(name = "product_id") Long productId,
                                                          @PathVariable(name = "quantity") int quantity) {
        Location location = locationService.addProductToWarehouse(warehouseId, productId, quantity);
        return new ResponseEntity<>(location, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouse() {
        List<Warehouse> warehouses = warehouseService.getAllWarehouse();
        return new ResponseEntity<>(warehouses, HttpStatus.OK);
    }

    @GetMapping("/{warehouse_id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable(name = "warehouse_id") Long warehouseId) {
        Warehouse warehouse = warehouseService.getWarehouseById(warehouseId);
        return new ResponseEntity<>(warehouse, HttpStatus.OK);
    }

    @PostMapping("/city/{city_id}")
    public ResponseEntity<Warehouse> createWarehouse(@PathVariable(name = "city_id") Long cityId,
                                                     @RequestBody AddressRequest addressRequest) {
        Warehouse warehouse = warehouseService.createWarehouse(addressRequest, cityId);
        return new ResponseEntity<>(warehouse, HttpStatus.CREATED);
    }

    @PostMapping("/{warehouse_id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable(name = "warehouse_id") Long warehouseId,
                                                     @RequestBody WarehouseDTO warehouseDTO) {
        Warehouse warehouse = warehouseService.updateWarehouse(warehouseId, warehouseDTO);
        return new ResponseEntity<>(warehouse, HttpStatus.OK);
    }

    @GetMapping("/city/{city_id}")
    public ResponseEntity<List<Warehouse>> getAllWarehousesByCityId(@PathVariable(name = "city_id") Long cityId) {
        List<Warehouse> warehouses = warehouseService.getAllWarehousesByCityId(cityId);
        return new ResponseEntity<>(warehouses, HttpStatus.OK);
    }

    @DeleteMapping("/{warehouse_id}")
    public ResponseEntity<HttpStatus> deleteWarehouseById(@PathVariable(name = "warehouse_id") Long warehouseId) {
        warehouseService.deleteWarehouseById(warehouseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
