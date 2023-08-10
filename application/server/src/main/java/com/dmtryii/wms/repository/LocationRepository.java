package com.dmtryii.wms.repository;

import com.dmtryii.wms.model.Location;
import com.dmtryii.wms.model.key.LocationKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, LocationKey> {
    Location findLocationByWarehouseIdAndProductId(Long warehouseId, Long productId);
    List<Location> findLocationByWarehouseId(Long warehouseId);
}
