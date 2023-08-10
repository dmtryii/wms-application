package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.request.AddressUpdateRequest;
import com.dmtryii.wms.dto.request.CompanyCreateRequest;
import com.dmtryii.wms.exception.ResourceNotFoundException;
import com.dmtryii.wms.model.Address;
import com.dmtryii.wms.model.Company;
import com.dmtryii.wms.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final AddressService addressService;

    public Company getById(Long id) {
        return companyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("The company not fount by id: " + id)
        );
    }

    public Company create(CompanyCreateRequest request) {
        Company companyResponse = Company.builder()
                .name(request.getName())
                .address(new Address())
                .build();
        return companyRepository.save(companyResponse);
    }

    public Company updateAddress(Long companyId, AddressUpdateRequest request) {
        Company company = getById(companyId);
        Address address = company.getAddress();

        addressService.update(address.getId(), request);
        company.setAddress(address);

        return companyRepository.save(company);
    }
}
