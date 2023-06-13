package com.dmtryii.wms.repository;

import com.dmtryii.wms.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
