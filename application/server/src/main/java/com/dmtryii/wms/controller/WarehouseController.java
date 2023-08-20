package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.StockDTO;
import com.dmtryii.wms.dto.request.DoAssemblyRequest;
import com.dmtryii.wms.dto.response.LocationDTO;
import com.dmtryii.wms.dto.response.WarehouseDTO;
import com.dmtryii.wms.dto.request.AddressUpdateRequest;
import com.dmtryii.wms.dto.request.WarehouseCreateRequest;
import com.dmtryii.wms.exception.ResourceNotCreatedException;
import com.dmtryii.wms.exception.ResourceNotUpdatedException;
import com.dmtryii.wms.exception.handle_exception.BadRequestRecorder;
import com.dmtryii.wms.model.Location;
import com.dmtryii.wms.model.Stock;
import com.dmtryii.wms.model.Warehouse;
import com.dmtryii.wms.service.LocationService;
import com.dmtryii.wms.service.StockService;
import com.dmtryii.wms.service.WarehouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/warehouses")
@RequiredArgsConstructor
public class WarehouseController {
    private final WarehouseService warehouseService;
    private final StockService stockService;
    private final LocationService locationService;
    private final ModelMapper modelMapper;
    private final BadRequestRecorder errorRecorder;

    @PostMapping("{warehouse_id}/stock")
    public ResponseEntity<List<StockDTO>> checkItems(@PathVariable(name = "warehouse_id") Long warehouseId) {
        List<StockDTO> stocks = stockService.updateItemsByWarehouseId(warehouseId)
                .stream()
                .map(this::map)
                .toList();
        return new ResponseEntity<>(stocks, HttpStatus.OK);
    }

    @PostMapping("{warehouse_id}/products")
    public ResponseEntity<LocationDTO> doAssemblyProduct(@PathVariable(name = "warehouse_id") Long warehouseId,
                                                         @RequestBody @Valid DoAssemblyRequest request,
                                                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errors = errorRecorder.getRecordedErrors(bindingResult);
            throw new ResourceNotCreatedException(errors);
        }
        Location location = locationService.doAssemblyProduct(warehouseId, request);
        return new ResponseEntity<>(
                map(location),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<WarehouseDTO>> getAllWarehouse() {
        List<WarehouseDTO> warehouses = warehouseService.getAllWarehouse()
                .stream()
                .map(this::map)
                .toList();
        return new ResponseEntity<>(warehouses, HttpStatus.OK);
    }

    @GetMapping("/{warehouse_id}")
    public ResponseEntity<WarehouseDTO> getWarehouseById(@PathVariable(name = "warehouse_id") Long warehouseId) {
        Warehouse warehouse = warehouseService.getWarehouseById(warehouseId);
        return new ResponseEntity<>(
                map(warehouse),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<WarehouseDTO> createWarehouse(@RequestBody @Valid WarehouseCreateRequest request,
                                                        BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errors = errorRecorder.getRecordedErrors(bindingResult);
            throw new ResourceNotCreatedException(errors);
        }
        Warehouse warehouse = warehouseService.createWarehouse(request);
        return new ResponseEntity<>(
                map(warehouse),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/{warehouse_id}/address")
    public ResponseEntity<WarehouseDTO> updateWarehouseAddress(@PathVariable(name = "warehouse_id") Long warehouseId,
                                                               @RequestBody @Valid AddressUpdateRequest request,
                                                               BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errors = errorRecorder.getRecordedErrors(bindingResult);
            throw new ResourceNotUpdatedException(errors);
        }
        Warehouse warehouse = warehouseService.updateAddress(warehouseId, request);
        return new ResponseEntity<>(
                map(warehouse),
                HttpStatus.OK
        );
    }

    @GetMapping("/cites/{city_id}")
    public ResponseEntity<List<WarehouseDTO>> getAllWarehousesByCityId(@PathVariable(name = "city_id") Long cityId) {
        List<WarehouseDTO> warehouses = warehouseService.getAllWarehousesByCityId(cityId)
                .stream()
                .map(this::map)
                .toList();
        return new ResponseEntity<>(warehouses, HttpStatus.OK);
    }

    @DeleteMapping("/{warehouse_id}")
    public ResponseEntity<HttpStatus> deleteWarehouseById(@PathVariable(name = "warehouse_id") Long warehouseId) {
        warehouseService.deleteWarehouseById(warehouseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private WarehouseDTO map(Warehouse warehouse) {
        return modelMapper.map(warehouse, WarehouseDTO.class);
    }
    private StockDTO map(Stock stock) {
        return modelMapper.map(stock, StockDTO.class);
    }
    private LocationDTO map(Location location) {
        return modelMapper.map(location, LocationDTO.class);
    }
}
