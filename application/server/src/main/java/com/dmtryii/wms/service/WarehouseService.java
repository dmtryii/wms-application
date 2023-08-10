package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.request.AddressCreateRequest;
import com.dmtryii.wms.dto.request.AddressUpdateRequest;
import com.dmtryii.wms.dto.request.WarehouseCreateRequest;
import com.dmtryii.wms.exception.ResourceNotFoundException;
import com.dmtryii.wms.model.Address;
import com.dmtryii.wms.model.Warehouse;
import com.dmtryii.wms.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseService {

    public static final Logger LOG = LoggerFactory.getLogger(Warehouse.class);
    private final WarehouseRepository warehouseRepository;
    private final AddressService addressService;
    private final ModelMapper modelMapper;

    public Warehouse createWarehouse(WarehouseCreateRequest request) {

        Address address = addressService.create(map(request));

        Warehouse warehouse = Warehouse.builder()
                .name(request.getName())
                .address(address)
                .build();
        return warehouseRepository.save(warehouse);
    }

    public Warehouse updateAddress(Long warehouseId, AddressUpdateRequest request) {
        Warehouse warehouse = getWarehouseById(warehouseId);
        Long addressId = warehouse.getAddress().getId();
        warehouse.setAddress(
                addressService.update(addressId, request)
        );
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

    public Warehouse getWarehouseById(Long warehouseId) {
        return warehouseRepository.findById(warehouseId).orElseThrow(
                () -> new ResourceNotFoundException("The warehouse not fount by id: " + warehouseId)
        );
    }

    public void deleteWarehouseById(Long id) {
        Warehouse warehouse = getWarehouseById(id);
        warehouseRepository.delete(warehouse);
        LOG.info("The warehouse from id {} was deleted", id);
    }

    public AddressCreateRequest map(WarehouseCreateRequest request) {
        return modelMapper.map(request, AddressCreateRequest.class);
    }
}
