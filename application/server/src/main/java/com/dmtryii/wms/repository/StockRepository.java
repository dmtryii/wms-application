package com.dmtryii.wms.repository;

import com.dmtryii.wms.model.Stock;
import com.dmtryii.wms.model.key.StockKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, StockKey> {
}
