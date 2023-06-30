package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.WarehouseDTO;
import com.dmtryii.wms.model.Warehouse;
import com.dmtryii.wms.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouse() {
        List<Warehouse> warehouses = warehouseService.getAllWarehouse();
        return new ResponseEntity<>(warehouses, HttpStatus.OK);
    }

    @GetMapping("{warehouse_id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable(name = "warehouse_id") Long warehouseId) {
        Warehouse warehouse = warehouseService.getWarehouseById(warehouseId);
        return new ResponseEntity<>(warehouse, HttpStatus.OK);
    }

    @PostMapping("{city_id}/city")
    public ResponseEntity<Warehouse> createWarehouse(@PathVariable(name = "city_id") Long cityId,
                                                     @RequestBody WarehouseDTO warehouseDTO) {
        Warehouse warehouse = warehouseService.createWarehouse(cityId, warehouseDTO);
        return new ResponseEntity<>(warehouse, HttpStatus.CREATED);
    }

    @PostMapping("{warehouse_id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable(name = "warehouse_id") Long warehouseId,
                                                     @RequestBody WarehouseDTO warehouseDTO) {
        Warehouse warehouse = warehouseService.updateWarehouse(warehouseId, warehouseDTO);
        return new ResponseEntity<>(warehouse, HttpStatus.OK);
    }

    @GetMapping("{city_id}/city")
    public ResponseEntity<List<Warehouse>> getAllWarehousesByCityId(@PathVariable(name = "city_id") Long cityId) {
        List<Warehouse> warehouses = warehouseService.getAllWarehousesByCityId(cityId);
        return new ResponseEntity<>(warehouses, HttpStatus.OK);
    }

    @DeleteMapping("{warehouse_id}")
    public ResponseEntity<HttpStatus> deleteWarehouseById(@PathVariable(name = "warehouse_id") Long warehouseId) {
        warehouseService.deleteWarehouseById(warehouseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
