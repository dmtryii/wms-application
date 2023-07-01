package com.dmtryii.wms.repository;

import com.dmtryii.wms.model.Location;
import com.dmtryii.wms.model.key.LocationKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, LocationKey> {
    Location findLocationByWarehouseIdAndProductId(Long warehouseId, Long productId);
    List<Location> findLocationByWarehouseId(Long warehouseId);
}
