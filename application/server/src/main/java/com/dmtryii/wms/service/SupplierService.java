package com.dmtryii.wms.service;

import com.dmtryii.wms.dto.request.SupplierRequest;
import com.dmtryii.wms.exception.RoleException;
import com.dmtryii.wms.exception.UserNotFoundException;
import com.dmtryii.wms.model.Company;
import com.dmtryii.wms.model.Supplier;
import com.dmtryii.wms.model.User;
import com.dmtryii.wms.model.enums.ERole;
import com.dmtryii.wms.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {

    public static final Logger LOG = LoggerFactory.getLogger(Supplier.class);
    private final SupplierRepository supplierRepository;
    private final CompanyService companyService;
    private final UserService userService;

    public Supplier createSupplier(SupplierRequest supplierRequest, Principal principal) {

        String username = supplierRequest.getUsername();
        Supplier _supplier = supplierRepository.findSupplierByUserUsername(username);

        if(_supplier != null) {
            throw new RoleException("The user already has this role");
        }
        User user = userService.getUserByUsername(username);
        Long companyId = supplierRequest.getCompanyId();

        User whoCreated = userService.getUserByPrincipal(principal);
        user.getRoles().add(ERole.SUPPLIES);

        Company company = companyService.getById(companyId);

        Supplier supplier = Supplier.builder()
                .company(company)
                .user(user)
                .whoCreated(whoCreated)
                .createData(LocalDateTime.now())
                .build();
        return supplierRepository.save(supplier);
    }

    public Supplier getSupplierById(Long supplierId) {
        return supplierRepository.findById(supplierId).orElseThrow(
                () -> new UserNotFoundException("Supplier not fount by id: " + supplierId)
        );
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierByPrincipal(Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        return supplierRepository.findSupplierByUser(user);
    }
}
