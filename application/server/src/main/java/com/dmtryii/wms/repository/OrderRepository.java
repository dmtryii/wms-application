package com.dmtryii.wms.repository;

import com.dmtryii.wms.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
