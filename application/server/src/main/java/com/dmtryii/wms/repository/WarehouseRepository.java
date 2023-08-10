package com.dmtryii.wms.repository;

import com.dmtryii.wms.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    Warehouse findWarehouseByName(String name);
}
