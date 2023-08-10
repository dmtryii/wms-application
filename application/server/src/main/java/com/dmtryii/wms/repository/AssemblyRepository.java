package com.dmtryii.wms.repository;

import com.dmtryii.wms.model.Assembly;
import com.dmtryii.wms.model.key.AssemblyKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssemblyRepository extends JpaRepository<Assembly, AssemblyKey> {
    List<Assembly> findAssembliesByProductId(Long productId);
    Assembly findAssembliesByProductIdAndItemId(Long productId, Long itemId);
    void deleteAllAssemblyByProductId(Long productId);
    void deleteAssemblyByProductIdAndItemId(Long productId, Long itemId);
}
