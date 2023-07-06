package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.request.AddressRequest;
import com.dmtryii.wms.dto.WarehouseDTO;
import com.dmtryii.wms.exception.NotFoundException;
import com.dmtryii.wms.model.Address;
import com.dmtryii.wms.model.OrderLine;
import com.dmtryii.wms.model.Warehouse;
import com.dmtryii.wms.repository.AddressRepository;
import com.dmtryii.wms.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseService {
    public static final Logger LOG = LoggerFactory.getLogger(OrderLine.class);
    private final WarehouseRepository warehouseRepository;
    private final CityService cityService;
    private final AddressRepository addressRepository;

    public Warehouse createWarehouse(AddressRequest addressRequest, Long cityId) {

        Address address = Address.builder()
                .currentName(addressRequest.currentAddressName())
                .city(cityService.getCityById(cityId))
                .build();
        addressRepository.save(address);

        Warehouse warehouse = new Warehouse();
        warehouse.setAddress(address);

        return warehouseRepository.save(warehouse);
    }

    public Warehouse updateWarehouse(Long warehouseId, WarehouseDTO warehouseDTO) {
        Warehouse warehouse = getWarehouseById(warehouseId);
        warehouse.setName(warehouseDTO.name());
        warehouse.setAddress(warehouse.getAddress());

        LOG.info("The warehouse from ID {} has been updated", warehouseId);
        return warehouseRepository.save(warehouse);
    }

    public List<Warehouse> getAllWarehousesByCityId(Long cityId) {
        List<Warehouse> warehouses = getAllWarehouse();
        return warehouses.stream().filter(
                w -> w.getAddress()
                        .getCity()
                        .getId().equals(cityId)).toList();
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
