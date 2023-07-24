package com.dmtryii.wms.repository;

import com.dmtryii.wms.model.Supplier;
import com.dmtryii.wms.model.Supply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplyRepository extends JpaRepository<Supply, Long> {
    List<Supply> findAllBySupplier(Supplier supplier);
}
