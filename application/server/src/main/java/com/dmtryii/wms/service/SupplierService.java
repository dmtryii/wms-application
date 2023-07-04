package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.SupplierDTO;
import com.dmtryii.wms.model.Supplier;
import com.dmtryii.wms.model.User;
import com.dmtryii.wms.model.enums.ERole;
import com.dmtryii.wms.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final UserService userService;

    public Supplier createSupplier(SupplierDTO supplierDTO) {

        String username = supplierDTO.getUsername();
        String companyName = supplierDTO.getCompanyName();

        Supplier _supplier = supplierRepository.findSupplierByUserUsername(username);

        if(_supplier != null) {
            _supplier.setCompanyName(companyName);
            return supplierRepository.save(_supplier);
        }

        User user = userService.getUserByUsername(username);
        user.getRoles().add(ERole.SUPPLIES);

        Supplier supplier = new Supplier();
        supplier.setCompanyName(companyName);
        supplier.setUser(user);
        return supplierRepository.save(supplier);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }
}
