package com.dmtryii.wms.repository;

import com.dmtryii.wms.model.SupplyLine;
import com.dmtryii.wms.model.key.SupplyLineKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyLineRepository extends JpaRepository<SupplyLine, SupplyLineKey> {
}
