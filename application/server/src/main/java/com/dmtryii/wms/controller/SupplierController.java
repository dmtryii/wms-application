package com.dmtryii.wms.controller;

import com.dmtryii.wms.dto.response.CompanyDTO;
import com.dmtryii.wms.dto.request.AddressUpdateRequest;
import com.dmtryii.wms.dto.request.CompanyCreateRequest;
import com.dmtryii.wms.exception.ResourceNotUpdatedException;
import com.dmtryii.wms.exception.handle_exception.BadRequestRecorder;
import com.dmtryii.wms.model.Company;
import com.dmtryii.wms.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/suppliers")
public class SupplierController {
    private final CompanyService companyService;
    private final ModelMapper modelMapper;
    private final BadRequestRecorder errorRecorder;

    @PostMapping("/company")
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody @Valid CompanyCreateRequest request,
                                                    BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            String errors = errorRecorder.getRecordedErrors(bindingResult);
            throw new ResourceNotUpdatedException(errors);
        }

        Company company = companyService.create(request);

        return new ResponseEntity<>(
                mapCompany(company),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/company/{company_id}")
    public ResponseEntity<CompanyDTO> updateCompanyAddress(@PathVariable(name = "company_id") Long companyId,
                                                           @RequestBody @Valid AddressUpdateRequest request,
                                                           BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errors = errorRecorder.getRecordedErrors(bindingResult);
            throw new ResourceNotUpdatedException(errors);
        }

        Company company = companyService.updateAddress(companyId, request);

        return new ResponseEntity<>(
                mapCompany(company),
                HttpStatus.OK
        );
    }

    public CompanyDTO mapCompany(Company company) {
        return modelMapper.map(company, CompanyDTO.class);
    }
}
