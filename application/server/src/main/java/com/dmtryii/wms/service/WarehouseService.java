package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.WarehouseDTO;
import com.dmtryii.wms.exception.NotFoundException;
import com.dmtryii.wms.model.OrderLine;
import com.dmtryii.wms.model.Warehouse;
import com.dmtryii.wms.repository.WarehouseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {
    public static final Logger LOG = LoggerFactory.getLogger(OrderLine.class);
    private final WarehouseRepository warehouseRepository;
    private final CityService cityService;

    @Autowired
    public WarehouseService(WarehouseRepository warehouseRepository, CityService cityService) {
        this.warehouseRepository = warehouseRepository;
        this.cityService = cityService;
    }

    public Warehouse createWarehouse(Long cityId, WarehouseDTO warehouseDTO) {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(warehouseDTO.getName());
        warehouse.setAddress(warehouseDTO.getAddress());
        warehouse.setCity(
                cityService.getCityById(cityId)
        );

        return warehouseRepository.save(warehouse);
    }

    public Warehouse updateWarehouse(Long warehouseId, WarehouseDTO warehouseDTO) {
        Warehouse warehouse = getWarehouseById(warehouseId);
        warehouse.setName(warehouseDTO.getName());
        warehouse.setAddress(warehouse.getAddress());

        LOG.info("The warehouse from ID {} has been updated", warehouseId);
        return warehouseRepository.save(warehouse);
    }

    public List<Warehouse> getAllWarehousesByCityId(Long cityId) {
        List<Warehouse> warehouses = getAllWarehouse();
        return warehouses.stream().filter(
                w -> w.getCity().getId().equals(cityId)).toList();
    }

    public List<Warehouse> getAllWarehouse() {
        return warehouseRepository.findAll();
    }

    public Warehouse getWarehouseByName(String name) {
        return warehouseRepository.findWarehouseByName(name);
    }

    public Warehouse getWarehouseById(Long warehouseId) {
        return warehouseRepository.findById(warehouseId).orElseThrow(
                () -> new NotFoundException("The warehouse was not found by this id")
        );
    }

    public void deleteWarehouseById(Long warehouseId) {
        warehouseRepository.deleteById(warehouseId);
        LOG.info("The warehouse from id {} was deleted", warehouseId);
    }
}
