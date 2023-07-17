package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.SupplierDTO;
import com.dmtryii.wms.dto.request.SupplierRequest;
import com.dmtryii.wms.dto_mapper.SupplierDTOMapper;
import com.dmtryii.wms.exception.UserNotFoundException;
import com.dmtryii.wms.model.Supplier;
import com.dmtryii.wms.model.User;
import com.dmtryii.wms.model.enums.ERole;
import com.dmtryii.wms.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierService {
    public static final Logger LOG = LoggerFactory.getLogger(Supplier.class);
    private final SupplierRepository supplierRepository;
    private final UserService userService;
    private final SupplierDTOMapper supplierDTOMapper;

    public Supplier createSupplier(SupplierRequest supplierRequest) {

        String username = supplierRequest.username();
        String companyName = supplierRequest.companyName();

        Supplier _supplier = supplierRepository.findSupplierByUserUsername(username);

        if(_supplier != null) {
            _supplier.setCompanyName(companyName);
            return supplierRepository.save(_supplier);
        }

        User user = userService.getUserByUsername(username);
        user.getRoles().add(ERole.SUPPLIES);

        Supplier supplier = new Supplier();
        supplier.setCompanyName(companyName);
        supplier.setRating(0.0);
        supplier.setUser(user);
        return supplierRepository.save(supplier);
    }

    public Supplier getSupplierById(Long supplierId) {
        return supplierRepository.findById(supplierId).orElseThrow(
                () -> new UserNotFoundException("Supplier not fount by id: " + supplierId)
        );
    }

    public List<SupplierDTO> getAllSuppliers() {
        return supplierRepository.findAll()
                .stream()
                .map(supplierDTOMapper)
                .collect(Collectors.toList());
    }
}
