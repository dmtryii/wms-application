package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.request.AddressCreateRequest;
import com.dmtryii.wms.dto.request.AddressUpdateRequest;
import com.dmtryii.wms.exception.ResourceNotFoundException;
import com.dmtryii.wms.model.Address;
import com.dmtryii.wms.model.City;
import com.dmtryii.wms.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    public final CityService cityService;

    public Address getById(Long id) {
        return addressRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("The address not fount by id: " + id)
        );
    }

    public Address update(Long addressId, AddressUpdateRequest request) {
        City city = cityService.getCityById(request.getCityId());

        Address address = getById(addressId);
        address.setStreetName(request.getStreetName());
        address.setStreetNumber(request.getStreetNumber());
        address.setDetails(request.getDetails());
        address.setCity(city);

        return addressRepository.save(address);
    }

    public Address create(AddressCreateRequest request) {

        City city = cityService.getCityById(request.getCityId());

        Address address = Address.builder()
                .streetName(request.getStreetName())
                .streetNumber(request.getStreetNumber())
                .details(request.getDetails())
                .city(city)
                .build();

        return addressRepository.save(address);
    }
}
