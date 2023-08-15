package com.dmtryii.wms.repository;

import com.dmtryii.wms.model.SupplyOrder;
import com.dmtryii.wms.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplyOrderRepository extends JpaRepository<SupplyOrder, Long> {
    List<SupplyOrder> findAllByDeliveryOrder_Employee_Warehouse(Warehouse warehouse);
}
