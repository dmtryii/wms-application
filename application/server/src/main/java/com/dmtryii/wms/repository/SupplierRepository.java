package com.dmtryii.wms.repository;

import com.dmtryii.wms.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Supplier findSupplierByUserUsername(String username);
}
