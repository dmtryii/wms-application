package com.dmtryii.wms.repository;

import com.dmtryii.wms.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
