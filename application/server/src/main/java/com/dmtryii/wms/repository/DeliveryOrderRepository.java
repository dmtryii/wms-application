package com.dmtryii.wms.repository;

import com.dmtryii.wms.model.DeliveryOrder;
import com.dmtryii.wms.model.enums.EState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder, Long> {
    List<DeliveryOrder> findAllByStateEquals(EState state);
}
