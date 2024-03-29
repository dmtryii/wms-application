package com.dmtryii.wms.repository;

import com.dmtryii.wms.model.Supplier;
import com.dmtryii.wms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Supplier findSupplierByUserUsername(String username);
    Supplier findSupplierByUser(User user);
}
