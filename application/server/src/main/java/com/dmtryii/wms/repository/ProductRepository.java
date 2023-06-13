package com.dmtryii.wms.repository;

import com.dmtryii.wms.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
