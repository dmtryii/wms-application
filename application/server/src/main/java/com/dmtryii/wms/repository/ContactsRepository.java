package com.dmtryii.wms.repository;

import com.dmtryii.wms.model.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactsRepository extends JpaRepository<Contacts, Long> {
}
