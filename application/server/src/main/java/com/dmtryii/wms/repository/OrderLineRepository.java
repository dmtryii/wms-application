package com.dmtryii.wms.repository;

import com.dmtryii.wms.model.OrderLine;
import com.dmtryii.wms.model.key.OrderLineKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, OrderLineKey> {
}
