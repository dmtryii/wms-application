package com.dmtryii.wms.repository;

import com.dmtryii.wms.model.Supply;
import com.dmtryii.wms.model.key.SupplyKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyRepository extends JpaRepository<Supply, SupplyKey> {
}
