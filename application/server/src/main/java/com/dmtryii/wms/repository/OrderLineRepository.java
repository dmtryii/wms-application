package com.dmtryii.wms.repository;

import com.dmtryii.wms.model.Order;
import com.dmtryii.wms.model.OrderLine;
import com.dmtryii.wms.model.Product;
import com.dmtryii.wms.model.key.OrderLineKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, OrderLineKey> {
    OrderLine findOrderLineByOrderAndProduct(Order order, Product product);
}
